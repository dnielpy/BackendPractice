package com.example.demo.Auth;


import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserEntity user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse register(RegisterRequest request) {
        UserEntity user = new UserEntity(request.getEmail(), request.getPassword(), 0.0);
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }
}

