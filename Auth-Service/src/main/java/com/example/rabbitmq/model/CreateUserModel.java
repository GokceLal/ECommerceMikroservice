package com.example.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateUserModel implements Serializable {
    private String userName;
    private String email;
    private Long authId;
}
