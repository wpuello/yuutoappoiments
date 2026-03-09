package com.yuutoap.Appoiments.service.auth;

import com.yuutoap.Appoiments.config.security.jwt.JwtService;
import com.yuutoap.Appoiments.config.tenant.TenantContext;
import com.yuutoap.Appoiments.dto.auth.AuthResponseDTO;
import com.yuutoap.Appoiments.model.security.User;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
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
    private final TenantRepository tenantRepository;
    private final JwtService jwtService;

    public AuthResponseDTO login(String tenant,String email, String password){


        //validar que el tenant exista
        tenantRepository.findBySlug(tenant)
                .orElseThrow(() -> new RuntimeException("Tenant no existe: " + tenant));

        //guardar tenant en contexto
        TenantContext.setTenant(tenant);

        //Autenticar usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        //Buscar el Usuario
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // validar que el usuario pertenece a ese tenant
        if(!user.getTenant().getSlug().equals(tenant)){
            throw new RuntimeException("El usuario no pertenece al tenant: " + tenant);
        }


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

    public AuthResponseDTO refreshToken(String tenant,String refreshToken){

        // validar tenant
        tenantRepository.findBySlug(tenant)
                .orElseThrow(() -> new RuntimeException("Tenant no existe: " + tenant));

        //guardar tenant en contexto
        TenantContext.setTenant(tenant);

        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getTenant().getSlug().equals(tenant)){
            throw new RuntimeException("El usuario no pertenece al tenant: " + tenant);
        }

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

