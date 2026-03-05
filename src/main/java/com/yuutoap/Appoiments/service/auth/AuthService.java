package com.yuutoap.Appoiments.service.auth;

import com.yuutoap.Appoiments.config.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(String email, String password){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        return jwtService.generateToken(email, Map.of());
    }

}
