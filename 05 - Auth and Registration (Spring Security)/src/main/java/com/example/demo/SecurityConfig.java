package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .requestMatchers("home").permitAll()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true).permitAll()
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();

        return http.build();
    }
}
