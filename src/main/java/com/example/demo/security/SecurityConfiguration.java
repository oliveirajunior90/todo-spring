package com.example.demo.security;

import com.example.demo.auth.CustomAuthenticationManager;
import com.example.demo.auth.JWTAuthenticationFilter;
import com.example.demo.auth.JWTAuthorizationFilter;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    private String SIGN_IN_URL = "/login";
    private String SIGN_UP_URL = "/signup";

    private final UserRepository repository;

    SecurityConfiguration(UserRepository repository) {
        this.repository = repository;
    }

    private UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl(repository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
        CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(userDetailsService(), passwordEncoder());
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(customAuthenticationManager))
                .addFilter(new JWTAuthorizationFilter(customAuthenticationManager))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
