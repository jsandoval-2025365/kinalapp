package com.justinsandoval.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests( auth ->
                        auth
                                .requestMatchers("/css/**", "/js/**", "/login").permitAll()
                                .requestMatchers("/principal").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ).formLogin(
                        auth ->
                                auth
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/principal", true)
                                        .permitAll()
                );
        return http.build();
    }
}
