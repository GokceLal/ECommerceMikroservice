package com.example.service;

import com.example.domain.User;
import com.example.dto.request.UserSaveRequestDto;
import com.example.dto.request.UserUpdateRequestDto;
import com.example.exception.ErrorType;
import com.example.exception.UserServiceException;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    public void save(UserSaveRequestDto dto){
        userRepository.save(User.builder()
                        .userName(dto.getUserName())
                        .email(dto.getEmail())
                        .authId(dto.getAuthId())
                .build());


        /**
         * Bu işlem exception fırlatabilir bu nedenle Object null kontrolu
         */
        Objects.requireNonNull(cacheManager.getCache("user-find-all")).clear();

    }

    public void update(UserUpdateRequestDto dto) {
        /**
         * dto içinde gelen user id bilgisi ile repository de parametre geçerek,
         * bu id ye sahip bir kullanıcı olup olmadığı bilgisini çektik
         */
        Optional<User> user = userRepository.findById(dto.getId());
        /**
         * Eğer id si verilen kullanıcı bulunamaz ise hata fırlatır
         */
        if(user.isEmpty())
            throw new UserServiceException(ErrorType.ERROR_INVALID_USER_ID);
        /**
         * Her şey yolunda ise kullanıcıyı öncelikle optional içinde çıkartırız ve dto
         * içinden gelen parametreleri güncellenecek  kullanıcı bilgileri ile değiştiririz
         */
        User updateUser = user.get();
        updateUser.setName(dto.getName());
        updateUser.setAbout(dto.getAbout());
        updateUser.setPhoto(dto.getPhoto());
        updateUser.setPhone(dto.getPhone());
        userRepository.save(updateUser);
        Objects.requireNonNull(cacheManager.getCache("user-find-all")).clear();

    }
@Cacheable(value = "user-find-all")
    public List<User> findAll() {
        return userRepository.findAll();
    }
@Cacheable(value = "get-string")
    public String getString(String ad){
        String createString = ad.toUpperCase().concat(" Merhaba");
        try {
            Thread.sleep(3000L);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return createString;
    }
}
