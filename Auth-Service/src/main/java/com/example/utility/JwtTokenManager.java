package com.example.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1.Kullanıcılara token oluşturmak
     * 2. Gelen Token bilgilerini doğrulamak
     */

    private final String SECRET_KEY = "v}pibUur,[y%6PC#2naVMrxCN!@HEE}55t{1dC&.wtI-i]QMf%";
    private final String ISSUER = "JavaAuth";

    private final Long EXDATE = 1000L * 40;
    public Optional<String>createToken(Long authId) {
        String token;
        try {
            token= JWT.create().withAudience()
                    .withClaim("authId", authId)// claim nesneleri şifreli değildir herkes görebilir
                    .withClaim("liste", List.of("hello","how","are","you")) //key-> value şeklinde açık degerler tutmak için kullanılır
                    .withIssuer(ISSUER)//oluşturulan kişi
                    .withIssuedAt(new Date())//oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXDATE))//token ın ne zaman süresi dolucak
                    .sign(Algorithm.HMAC512(SECRET_KEY));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }



    }

    public Optional<Long>validateToken(String token){
        try{
            /**
             * Token doğrularken ve içinden bilgileri çekerken ilk olarak
             * 1- şifreleme algoritması kullanarak token verify edilmeli
             * 2- bu doğrulama işleminde süresinin dolup dolmadığında kontrol edilir
             * 3- sahipliği bizim mi
             * Bunlar okey ise token decode edilmiş oluyor. Sonrasında ise claim nesnelerinin
             * içinden bilgiler alınarak dönüş yapılır.
             */
            Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null){
                return Optional.empty();
            }
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
