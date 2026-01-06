package com.example.app.rls.serviceImpl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.rls.entity.User;
import com.example.app.rls.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found.."));
        
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            getAuthority(user)
        ); 
    }

    private Collection<? extends GrantedAuthority> getAuthority(User user) {
        
        return user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRolename()))
            .collect(Collectors.toSet());
    }
 
}