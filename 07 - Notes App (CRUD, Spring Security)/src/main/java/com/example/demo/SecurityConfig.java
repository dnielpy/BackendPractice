package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                request
                        //Without Auth
                        .requestMatchers("/user/**")
                        .permitAll()

                        //With Auth
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public UserDetailsService admins(PasswordEncoder passwordEncoder){
        List<Users> users_inbd = userRepository.findAll();
        List<UserDetails> admins = new ArrayList<>();

        for (Users value : users_inbd) {
            if (value.getIsadmin() != null && value.getIsadmin()) {
                UserDetails admin = User.builder()
                        .username(value.getUsername())
                        .password(passwordEncoder.encode(value.getPassword()))
                        .roles("ADMIN")
                        .build();
                admins.add(admin);
            }
        }

        return new InMemoryUserDetailsManager(admins);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
