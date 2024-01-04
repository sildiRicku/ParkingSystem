//package com.example.system.config;
//
//import com.example.system.security.JwtAuthenticationEntryPoint;
//import com.example.system.security.JwtRequestFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class JwtSecurityConfig {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private UserDetailsService jwtUserDetailsService;
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
//    }
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests().requestMatchers("/authenticate").permitAll().
//                requestMatchers("/login/login2").authenticated().and().
//                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // this filter is to validate the tokens with every request
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//    }
//
//
//}