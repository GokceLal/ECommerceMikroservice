package com.example.dto.request;

public record UserRequestDto(
        String id,
        Long authId,
        String email,
        String photo,
        String userName,
        String name,
        String phone,
        String about
) {

}
