package com.jakarta.jakarta.auth.controller;

import com.jakarta.jakarta.auth.dto.AuthResponseDto;
import com.jakarta.jakarta.auth.dto.LoginRequestDto;
import com.jakarta.jakarta.auth.dto.RegisterRequestDto;
import com.jakarta.jakarta.auth.service.AuthService;
import com.jakarta.jakarta.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Auth",
        description = "Endpoints for Auth"
)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<AuthResponseDto>> register(@RequestBody RegisterRequestDto request) {
        AuthResponseDto register = authService.register(request);
        return Response.success(HttpStatus.OK.value(), "Success", register);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthResponseDto>> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto login = authService.login(request);
        return Response.success(HttpStatus.OK.value(), "Login successful", login);
    }
}
