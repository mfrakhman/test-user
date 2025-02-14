package com.jakarta.jakarta.auth.service;


import com.jakarta.jakarta.auth.dto.AuthResponseDto;
import com.jakarta.jakarta.auth.dto.LoginRequestDto;
import com.jakarta.jakarta.auth.dto.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto register(RegisterRequestDto request);
    AuthResponseDto login(LoginRequestDto request);
}
