package com.example.demo.auth;

import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        CharSequence password = authentication.getCredentials().toString();

        UserDetails userPrincipal = userDetailsService.loadUserByUsername(username);

        boolean passwordMatches = passwordEncoder.matches(password, userPrincipal.getPassword());

        if (passwordMatches) {
            return new UsernamePasswordAuthenticationToken(username, password);
        }

        throw new BadCredentialsException("credenciais inválidas");
    }
}
