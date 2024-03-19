package com.example.service;

import com.example.dto.request.LoginRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.dto.request.UserSaveRequestDto;
import com.example.entity.Auth;
import com.example.manager.UserProfileManager;
import com.example.rabbitmq.model.CreateUserModel;
import com.example.rabbitmq.producer.CreateUserProducer;
import com.example.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    //private final UserProfileManager manager;
    private final CreateUserProducer createUserProducer;

    public Boolean register(RegisterRequestDto dto){
        Auth auth = Auth.builder()
                .password(dto.getPassword())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .isActive(true)
                .build();
        authRepository.save(auth);
        /**
         * Burada kullanıcıyı authDb ye kaydettikten sonra UserService e Profil
         * oluşturması için bilgilerini iletmemiz gereklidir
         */
//        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
//                .authId(auth.getId())
//                .userName(dto.getUserName())
//                .email(dto.getEmail())
//                .build();
//        manager.save(userSaveRequestDto);
        /**
         * DİKKAT
         * Burada userservice kayıt işlemi FeignClient ile yapılıyordu ancak bu işlem bütün riskleri barındırır
         * bu nedenle rabbitMQ ile sistmin sorunsuz ve kayıpsız bir şekilde iletilmesini sağlıyoruz
         */
        createUserProducer.sendMessage(CreateUserModel.builder()
                        .authId(auth.getId())
                        .email(dto.getEmail())
                        .userName(dto.getUserName())
                .build());
        return true;
    }

    public Optional<Auth> dologin(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        return auth;
    }
}
