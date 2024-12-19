package com.devano.jwt.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    String authenticate(String email, String password);
}
