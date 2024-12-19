package com.devano.jwt.service;


import com.devano.jwt.dto.auth.register.RegisterRequestDto;
import com.devano.jwt.dto.auth.register.RegisterResponseDto;

public interface UserService {

    RegisterResponseDto register(RegisterRequestDto registerRequestDto);

}
