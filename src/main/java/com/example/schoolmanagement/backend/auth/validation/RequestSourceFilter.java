package com.example.schoolmanagement.backend.auth.validation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestSourceFilter extends OncePerRequestFilter {

    @Value("${security.allowed-origins}")
    private List<String> allowedOrigins;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String origin = request.getHeader("Origin");

        if (origin != null && !allowedOrigins.contains(origin)) {
            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Request source not allowed"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}

