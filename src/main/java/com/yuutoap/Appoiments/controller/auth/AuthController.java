package com.yuutoap.Appoiments.controller.auth;

import com.yuutoap.Appoiments.config.security.jwt.JwtService;
import com.yuutoap.Appoiments.config.tenant.TenantContext;
import com.yuutoap.Appoiments.dto.auth.AuthResponseDTO;
import com.yuutoap.Appoiments.dto.auth.LoginRequestDTO;
import com.yuutoap.Appoiments.dto.auth.RefreshRequestDTO;
import com.yuutoap.Appoiments.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/{tenant}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @PathVariable String tenant,
            @RequestBody LoginRequestDTO request){

        // guardar tenant en contexto
        TenantContext.setTenant(tenant);

        AuthResponseDTO response = authService.login(
                tenant,
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(
            @PathVariable String tenant,
            @RequestBody RefreshRequestDTO request){

        // guardar tenant en contexto
        TenantContext.setTenant(tenant);

        return ResponseEntity.ok(
                authService.refreshToken(tenant,request.getRefreshToken())
        );
    }

}
