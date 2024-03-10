package com.example.service;

import com.example.domain.User;
import com.example.dto.request.UserSaveRequestDto;
import com.example.dto.request.UserUpdateRequestDto;
import com.example.exception.ErrorType;
import com.example.exception.UserServiceException;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(UserSaveRequestDto dto){
        userRepository.save(User.builder()
                        .userName(dto.getUserName())
                        .email(dto.getEmail())
                        .authId(dto.getAuthId())
                .build());
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

    }
}
