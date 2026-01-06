package com.example.app.rls.serviceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.rls.dao.JwtResponse;
import com.example.app.rls.dao.LoginRequest;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.RegisterRequest;
import com.example.app.rls.entity.JwtToken;
import com.example.app.rls.entity.Role;
import com.example.app.rls.entity.User;
import com.example.app.rls.jwt.JwtUtils;
import com.example.app.rls.repository.JwtTokenRepository;
import com.example.app.rls.repository.UserRepository;
import com.example.app.rls.service.AuthService;
import com.example.app.rls.service.RoleService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;


    @Override
    public MessageResponse register(RegisterRequest registerRequest) {
        
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exist..!");
        }
        
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exist..!");
        }

        Role role = roleService.getDefaultRole();
        
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Set.of(role))
                .build();

        userRepository.save(user);

        return MessageResponse.builder().message("User registered Successfully..!").build();
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        
        String login = loginRequest.getLogin().toLowerCase();

        User user = userRepository.findByUsernameOrEmail(login, login)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or email..!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password..!");
        }

        validateLoginSource(loginRequest.getLoginSource(), user);

        String token = jwtUtils.generateJwtToken(user);

        revokeAllUserTokens(user);

        JwtToken jwtToken = JwtToken.builder()
                .token(token)
                .user(user)
                .expired(false)
                .revoked(false)
                .issuedAt(Instant.now())
                .expiredAt(Instant.now().plusMillis(jwtUtils.getJwtExpiration()))
                .build();

        jwtTokenRepository.save(jwtToken);
        
        return JwtResponse.builder().token(token).build();        
    }

    private void validateLoginSource(String loginSource, User user) {

        if (loginSource == null || loginSource.trim().isEmpty()) {
            throw new IllegalArgumentException("Login source is required");
        }

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getRolename()));
        boolean isUser = user.getRoles().stream().anyMatch(role -> "ROLE_USER".equals(role.getRolename()));

        switch (loginSource.toUpperCase()) {

            case "ADMIN":
                if (!isAdmin) {
                    throw new IllegalArgumentException("Invalid login source");
                }
                break;

            case "USER":
                if (!isUser) {
                    throw new IllegalArgumentException("Invalid login source");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid login source");
        }
    }

    
    private void revokeAllUserTokens(User user) {
        
        List<JwtToken> activeTokens = jwtTokenRepository.findAllByUserAndExpiredFalseAndRevokedFalse(user);
        if (activeTokens.isEmpty()) {
            return;
        }
        activeTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        
        jwtTokenRepository.saveAll(activeTokens);
    }

    @Override
    public MessageResponse logout(String authorizationHeader) {

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new IllegalArgumentException("Authorization header is missing");
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header format");
        }

        String token = authorizationHeader.substring(7);

        JwtToken jwtToken = jwtTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (jwtToken.isExpired() || jwtToken.isRevoked()) {
            throw new IllegalArgumentException("Token already logged out");
        }

        jwtToken.setExpired(true);
        jwtToken.setRevoked(true);

        jwtTokenRepository.save(jwtToken);

        return MessageResponse.builder().message("Logged out successfully").build();

    }

}
