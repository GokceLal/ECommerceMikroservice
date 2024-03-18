package com.example.controller;

import com.example.dto.request.LoginRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.entity.Auth;
import com.example.exception.AuthServiceException;
import com.example.exception.ErrorType;
import com.example.service.AuthService;
import com.example.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


import static com.example.constant.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean>register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));

    }
@PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginRequestDto dto){
    Optional<Auth> auth = authService.dologin(dto);
    if(auth.isEmpty())
        throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
    Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
    if (token.isEmpty())
        throw new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN);
    return ResponseEntity.ok(token.get());
    }
}
