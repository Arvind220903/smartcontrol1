package com.arvind.smartcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API testing
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/hello", "/api/unlock").permitAll()
 // Allow access without login
                .anyRequest().authenticated()           // Protect other endpoints
            )
            .httpBasic(Customizer.withDefaults());      // Use HTTP Basic Auth (if needed)
        
        return http.build();
    }
}
