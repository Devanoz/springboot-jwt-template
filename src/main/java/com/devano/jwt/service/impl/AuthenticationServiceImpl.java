package com.devano.jwt.service.impl;

import com.devano.jwt.model.User;
import com.devano.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public String authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);
        Authentication authentication = authenticationManager.authenticate(token);
        if(!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Wrong username or password");
        }
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user.getEmail(),user.getRole().getName());
    }
}
