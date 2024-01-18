package com.devano.jwt.controller;

import com.devano.jwt.auth.JwtUtil;
import com.devano.jwt.dto.GlobalResponse;
import com.devano.jwt.dto.auth.login.LoginRequestDto;
import com.devano.jwt.dto.auth.login.LoginResponseDto;
import com.devano.jwt.dto.auth.register.RegisterRequestDto;
import com.devano.jwt.exception.UserNotFoundException;
import com.devano.jwt.model.User;
import com.devano.jwt.repository.UserRepository;
import com.devano.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<Object>> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            Optional<User> userOptionalByEmail = userRepository.findByEmail(loginRequest.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            User user;
            if(userOptionalByEmail.isPresent()){
                user = userOptionalByEmail.get();
            }else{
                throw new UserNotFoundException("email tidak ditemukan");
            }
            String token = jwtUtil.createToken(user);

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setEmail(user.getEmail());
            loginResponseDto.setToken(token);

            return ResponseEntity.ok(GlobalResponse.success("Login berhasil",loginResponseDto));

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalResponse.error("invalid username or password"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<Object>> register(@RequestBody RegisterRequestDto registerRequestDto){
        GlobalResponse<Object> registerResponse = userService.register(registerRequestDto);
        return ResponseEntity.ok(registerResponse);
    }
}
