package com.example.controller;

import com.example.domain.User;
import com.example.dto.request.UserSaveRequestDto;
import com.example.dto.request.UserUpdateRequestDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.constants.RestApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @Value("${userservice.deger2}")  // lombok olan değil
    private String deger2;
    @Value("${userservice.listem.iki}")
    private String iki;
    @GetMapping("/getApplicationProperties")

    public String getApplicationProperties(){
        log.info("Properties Bilgisi....: "+iki);
        System.out.printf("Console çıktı.....:"+iki);
        return deger2;
    }
    @PostMapping(ADD)
    public ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok().build();
    }
@PutMapping(UPDATE)
    public ResponseEntity<Void> update(UserUpdateRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();
    }
}
