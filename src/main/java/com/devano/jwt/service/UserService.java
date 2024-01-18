package com.devano.jwt.service;


import com.devano.jwt.dto.GlobalResponse;
import com.devano.jwt.dto.auth.login.LoginRequestDto;
import com.devano.jwt.dto.auth.register.RegisterRequestDto;

public interface UserService {

    GlobalResponse<Object> register(RegisterRequestDto registerRequestDto);

}
