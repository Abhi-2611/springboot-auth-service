package com.example.schoolmanagement.backend.auth.entity;


import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jwt_tokens", schema = "rls")
public class JwtToken {

    @Id
    @Column(name = "\"id\"", precision = 19)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jwt_tokens_seq_gen")
    @SequenceGenerator(name = "jwt_tokens_seq_gen", sequenceName = "jwt_tokens_seq", schema = "rls", allocationSize = 1)
    private Long id;

    @Column(name = "tokens")
    private String token;

    @Column(name = "expired")
    private boolean expired;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "issued_at")
    private Instant issuedAt;

    @Column(name = "expired_at")
    private Instant expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
