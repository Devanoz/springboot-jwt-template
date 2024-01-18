package com.devano.jwt.util;

import com.devano.jwt.model.Role;
import com.devano.jwt.repository.RoleRepository;
import com.devano.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PrepopulateDatabase implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role admin = new Role();
        admin.setName("ADMIN");


        Role user = new Role();
        user.setName("USER");
        roleRepository.saveAll(List.of(admin,user));
    }
}
