package com.example.domain;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document

public class User implements Serializable {
    @Id
    private String id;
    private Long authId;
    // Kullanıcı kayıt olurken Auth bilgilerini eşleştirmek üzere auth-id bilgilerini burada tutarız
    private String userName;

    private String email;
    private String photo;
    private String name;
    private String phone;
    private String about;

}
