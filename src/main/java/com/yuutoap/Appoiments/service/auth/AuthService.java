package com.yuutoap.Appoiments.service.auth;

import com.yuutoap.Appoiments.config.security.jwt.JwtService;
import com.yuutoap.Appoiments.dto.auth.AuthResponseDTO;
import com.yuutoap.Appoiments.model.security.User;
import com.yuutoap.Appoiments.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponseDTO login(String email, String password){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String,Object> claims = Map.of(
                "tenant", user.getTenant().getSlug(),
                "roles", user.getRoles()
                        .stream()
                        .map(r -> r.getName())
                        .toList(),
                "userId", user.getId()
        );

        String accessToken = jwtService.generateAccessToken(email, claims);
        String refreshToken = jwtService.generateRefreshToken(email);


        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponseDTO refreshToken(String refreshToken){

        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String,Object> claims = Map.of(
                "tenant", user.getTenant().getSlug(),
                "roles", user.getRoles()
                        .stream()
                        .map(r -> r.getName())
                        .toList(),
                "userId", user.getId()
        );

        String newAccessToken = jwtService.generateAccessToken(username, claims);

        return AuthResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }
    }

