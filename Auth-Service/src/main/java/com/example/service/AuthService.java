package com.example.service;

import com.example.dto.request.LoginRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.entity.Auth;
import com.example.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

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
        return true;
    }

    public Optional<Auth> dologin(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        return auth;
    }
}
