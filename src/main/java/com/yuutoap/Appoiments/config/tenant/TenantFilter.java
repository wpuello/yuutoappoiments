package com.yuutoap.Appoiments.config.tenant;


import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TenantFilter extends OncePerRequestFilter {

    private final TenantRepository tenantRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String uri = request.getRequestURI();

        // ejemplo: /api/v1/yuuto-system/auth/login
        String[] parts = uri.split("/");

        if (parts.length > 3) {

            String tenant = parts[3];

            boolean exists = tenantRepository.existsBySlug(tenant);

            if (!exists) {

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");

                response.getWriter().write("""
                    {
                      "error": "Tenant no Encontrado"
                    }
                """);

                return;
            }

            TenantContext.setTenant(tenant);
        }

        filterChain.doFilter(request, response);
    }
}
