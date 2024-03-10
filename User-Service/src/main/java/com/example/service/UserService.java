package com.example.service;

import com.example.domain.User;
import com.example.exception.UserServiceException;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }

}
