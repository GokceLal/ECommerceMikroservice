package com.example.utility;

import com.example.dto.request.UserRequestDto;
import com.example.manager.ElasticUserManager;
import com.example.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Component;

@Component //spring bu sınıftan nesne yaratması gerektiğini biliyor
@RequiredArgsConstructor
public class TestandRun {
    private final UserService userService;
    private final ElasticUserManager userManager;
   // @PostConstruct
public void start(){
    userService.findAll().forEach(user->{
        UserRequestDto dto = new UserRequestDto(
                user.getId(),
                user.getAuthId(),
                user.getEmail(),
                user.getPhoto(),
                user.getUserName(),
                user.getName(),
                user.getPhone(),
                user.getAbout()
        );
        userManager.save(dto);
    });
}


}
