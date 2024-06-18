package com.example.demo;

import com.example.demo.entitys.Users;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
                                        //Todos pueden acceder a /user/create sin loggearse
                                        .requestMatchers("/user/**")
                                        .permitAll()

                                        //USERS puedes acceder a /notes
                                        .requestMatchers("/notes")
                                        .hasRole("USER")

                                        //USERS puedes acceder a /lists
                                        .requestMatchers("/list")
                                        .hasRole("USER")

                                        //USERS puedes acceder a /lists
                                        .requestMatchers("/list/**")
                                        .hasRole("USER")

                                        //USERS puedes acceder a /users
                                        .requestMatchers("/user")
                                        .hasRole("USER")

                        //Los admins no deberian poder acceder a los datos de los usuarios
//                        //ADMINS puedes acceder a todo
//                        .requestMatchers("/**")
//                        .hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService admins(PasswordEncoder passwordEncoder) {
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
            } else {
                UserDetails admin = User.builder()
                        .username(value.getUsername())
                        .password(passwordEncoder.encode(value.getPassword()))
                        .roles("USER")
                        .build();
                admins.add(admin);
            }
        }
        return new InMemoryUserDetailsManager(admins);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
