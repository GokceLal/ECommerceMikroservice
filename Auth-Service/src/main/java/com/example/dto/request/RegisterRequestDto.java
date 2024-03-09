package com.example.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RegisterRequestDto {
    @Email
    @NotNull
    private String email;
    @Size(min = 3, max = 64)
    @NotNull
    private String userName;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
}
