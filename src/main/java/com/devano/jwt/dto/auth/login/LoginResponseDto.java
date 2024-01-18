package com.devano.jwt.dto.auth.login;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String email;
    private String token;
}
