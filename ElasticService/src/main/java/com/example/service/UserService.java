package com.example.service;

import com.example.domain.User;
import com.example.dto.request.UserRequestDto;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(UserRequestDto dto) {
        User user = User.builder()
                .id(dto.id())
                .authId(dto.authId())
                .email(dto.email())
                .userName(dto.userName())
                .about(dto.about())
                .phone(dto.phone())
                .photo(dto.photo())
                .name(dto.name())
                .build();
        userRepository.save(user);
    }
    public void update(UserRequestDto dto) {
        User user = User.builder()
                .id(dto.id())
                .authId(dto.authId())
                .email(dto.email())
                .userName(dto.userName())
                .about(dto.about())
                .phone(dto.phone())
                .photo(dto.photo())
                .name(dto.name())
                .build();
        userRepository.save(user);
    }
    public Iterable<User> getAll(){
        return userRepository.findAll();
    }
}
