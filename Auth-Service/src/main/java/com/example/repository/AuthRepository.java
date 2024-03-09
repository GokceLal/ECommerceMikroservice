package com.example.repository;

import com.example.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    //Bir kullanıcı adının varlığını sorgulıcaz
Optional<Auth> findOptionalByUserNameAndPassword(String userName, String password);
}
