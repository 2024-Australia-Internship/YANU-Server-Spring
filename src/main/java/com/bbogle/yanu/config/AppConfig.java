package com.bbogle.yanu.config;

import com.bbogle.yanu.repository.UserRepository;
import com.bbogle.yanu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class AppConfig {
    private final UserRepository userRepository;

    @Bean
    public UserService userService(){
        return new UserService(userRepository);
    }
}
