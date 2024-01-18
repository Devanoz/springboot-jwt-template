package com.devano.jwt.service.impl;

import com.devano.jwt.dto.GlobalResponse;
import com.devano.jwt.dto.auth.register.RegisterRequestDto;
import com.devano.jwt.exception.UserNotFoundException;
import com.devano.jwt.model.Role;
import com.devano.jwt.model.User;
import com.devano.jwt.repository.RoleRepository;
import com.devano.jwt.repository.UserRepository;
import com.devano.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public GlobalResponse<Object> register(RegisterRequestDto registerRequestDto) {
        Optional<Role> optionalRole = roleRepository.findById(registerRequestDto.getRoleId());

        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            User user = new User();
            user.setEmail(registerRequestDto.getEmail());
            user.setUsername(registerRequestDto.getUsername());
            String userPassword = registerRequestDto.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(userPassword));
            user.setRole(role);
            userRepository.save(user);
        }else{
            throw new UserNotFoundException("Role tidak ditemukan");
        }

        return GlobalResponse.success("Registrasi berhasil",null);
    }
}
