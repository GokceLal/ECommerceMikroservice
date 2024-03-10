package com.example.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserUpdateRequestDto {
    private String id;
    private String photo;
    private String name;
    private String phone;
    private String about;

}
