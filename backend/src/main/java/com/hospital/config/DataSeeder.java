package com.hospital.config;

import com.hospital.entity.User;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Creates a default admin account the first time the app starts. */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedAdmin(UserRepository users, PasswordEncoder encoder,
                                @Value("${app.admin.username}") String username,
                                @Value("${app.admin.password}") String password) {
        return args -> {
            if (!users.existsByUsername(username)) {
                User admin = new User();
                admin.setUsername(username);
                admin.setPassword(encoder.encode(password));
                admin.setEmail("admin@smarthospital.local");
                admin.setRole(User.Role.ADMIN);
                users.save(admin);
            }
        };
    }
}
