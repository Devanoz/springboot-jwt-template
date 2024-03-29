package com.devano.jwt.dto.auth.register;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
    private long roleId;
}
