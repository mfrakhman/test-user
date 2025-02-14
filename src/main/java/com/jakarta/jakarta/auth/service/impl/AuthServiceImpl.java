package com.jakarta.jakarta.auth.service.impl;

import com.jakarta.jakarta.auth.dto.AuthResponseDto;
import com.jakarta.jakarta.auth.dto.LoginRequestDto;
import com.jakarta.jakarta.auth.dto.RegisterRequestDto;
import com.jakarta.jakarta.auth.service.AuthService;
import com.jakarta.jakarta.auth.utils.JwtUtil;
import com.jakarta.jakarta.user.entity.User;
import com.jakarta.jakarta.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setUsername(user.getUsername());
        return response;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        User findUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String accessToken = jwtUtil.generateAccessToken(findUser.getEmail(), findUser.getId());

        AuthResponseDto response = new AuthResponseDto();
        response.setUsername(findUser.getUsername());
        response.setToken(accessToken);
        return response;
    }
}
