package com.example.rls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rls.entity.JwtToken;
import com.example.rls.entity.User;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    Optional<JwtToken> findByToken(String token);

    List<JwtToken> findAllByUserAndExpiredFalseAndRevokedFalse(User user);
}
