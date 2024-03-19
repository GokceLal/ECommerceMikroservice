package com.example.controller;

import com.example.domain.User;
import com.example.dto.request.UserRequestDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;
    @PostMapping(ADD)
    @CrossOrigin("*")
    public ResponseEntity<Void> save(@RequestBody UserRequestDto dto){

        userService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(UPDATE)

    public ResponseEntity<Void> update(@RequestBody UserRequestDto dto){
    userService.save(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GET_ALL)

    public ResponseEntity<Iterable<User>> getAll(){
        return ResponseEntity.ok().body(userService.getAll());

    }
}
