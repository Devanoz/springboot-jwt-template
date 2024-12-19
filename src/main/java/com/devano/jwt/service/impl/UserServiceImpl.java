package com.devano.jwt.service.impl;

import com.devano.jwt.dto.auth.register.RegisterRequestDto;
import com.devano.jwt.dto.auth.register.RegisterResponseDto;
import com.devano.jwt.exception.UserNotFoundException;
import com.devano.jwt.model.Role;
import com.devano.jwt.model.User;
import com.devano.jwt.repository.RoleRepository;
import com.devano.jwt.repository.UserRepository;
import com.devano.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        Optional<Role> optionalRole = roleRepository.findById(registerRequestDto.getRoleId());

        User savedUser;
        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            User user = new User();
            user.setEmail(registerRequestDto.getEmail());
            user.setUsername(registerRequestDto.getEmail());
            String userPassword = registerRequestDto.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(userPassword));
            user.setRole(role);
            savedUser = userRepository.save(user);
        }else{
            throw new UserNotFoundException("User not found");
        }

        return RegisterResponseDto.builder()
                .email(savedUser.getEmail())
                .role(savedUser.getRole().getName())
                .build();
    }
}
