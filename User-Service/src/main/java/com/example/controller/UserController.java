package com.example.controller;

import com.example.domain.User;
import com.example.dto.request.DefaultRequestDto;
import com.example.dto.request.UserSaveRequestDto;
import com.example.dto.request.UserUpdateRequestDto;
import com.example.exception.ErrorType;
import com.example.exception.UserServiceException;
import com.example.service.UserService;
import com.example.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.constants.RestApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;
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
    //Bütün kullanıcı bilgilerini getir
    @GetMapping(GET_ALL)
    public ResponseEntity<List<User>> getAll(DefaultRequestDto dto){
        Optional<Long> authId = jwtTokenManager.validateToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserServiceException(ErrorType.INTERNAL_TOKEN_ERROR);

        return ResponseEntity.ok(userService.findAll());
    }
//@GetMapping("/get-spring")
//    public ResponseEntity<String> getString(){
//        String createString = "Merhaba Spring";
//    /**
//     *  Aşağıda yazılan kod blogu bir işlemin uzun sürmesi durumunu simüle etmek için eklenmiştir
//     */
//    try {
//        Thread.sleep(3000L);
//    } catch (Exception e) {
//        log.error("Beklenmeyen thread hatası");
//    }
//    return ResponseEntity.ok(createString);
//
//}

    @GetMapping("/get-string")
    public  ResponseEntity<String> getString(String ad){
        return ResponseEntity.ok(userService.getString(ad));

    }


}
