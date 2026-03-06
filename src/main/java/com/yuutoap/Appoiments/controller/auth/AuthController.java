package com.yuutoap.Appoiments.controller.auth;

import com.yuutoap.Appoiments.config.security.jwt.JwtService;
import com.yuutoap.Appoiments.dto.auth.AuthResponseDTO;
import com.yuutoap.Appoiments.dto.auth.LoginRequestDTO;
import com.yuutoap.Appoiments.dto.auth.LoginResponseDTO;
import com.yuutoap.Appoiments.dto.auth.RefreshRequestDTO;
import com.yuutoap.Appoiments.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request){

        AuthResponseDTO response = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestBody RefreshRequestDTO request){

        return ResponseEntity.ok(
                authService.refreshToken(request.getRefreshToken())
        );
    }

}
