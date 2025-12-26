package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth

                // 🔓 PUBLIC
                .requestMatchers(
                        "/api/auth/**",
                        "/status",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/api-docs/**"
                ).permitAll()

                // 👤 HOLDERS
                .requestMatchers("/api/holders/**")
                    .hasAnyRole("ADMIN", "VERIFIER")

                // 🎓 CREDENTIALS
                .requestMatchers(HttpMethod.GET, "/api/credentials/**")
                    .hasAnyRole("ADMIN", "VERIFIER", "VIEWER")

                .requestMatchers(HttpMethod.POST, "/api/credentials/**")
                    .hasAnyRole("ADMIN", "VERIFIER")

                .requestMatchers(HttpMethod.PUT, "/api/credentials/**")
                    .hasAnyRole("ADMIN", "VERIFIER")

                // 🧪 VERIFICATION RULES
                .requestMatchers("/api/rules/**")
                    .hasRole("ADMIN")

                // ✅ VERIFICATION REQUESTS
                .requestMatchers("/api/verifications/**")
                    .hasAnyRole("ADMIN", "VERIFIER")

                // 🧾 AUDIT TRAIL (ADMIN ONLY)
                .requestMatchers("/api/audit/**")
                    .hasRole("ADMIN")

                // 🔒 EVERYTHING ELSE
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}
