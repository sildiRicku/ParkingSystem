package com.example.system.config;

import com.example.system.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@CrossOrigin
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(86400)
                        .userDetailsService(customUserDetailsService)
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
