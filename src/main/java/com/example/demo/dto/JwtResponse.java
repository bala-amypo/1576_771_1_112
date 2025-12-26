package com.example.demo.dto;

public class JwtResponse {

    private String token;
    private Long id;
    private String email;
    private String role;

    // REQUIRED by existing tests (DO NOT REMOVE)
    public JwtResponse(String token) {
        this.token = token;
    }

    // REQUIRED by your AuthController
    public JwtResponse(String token, Long id, String email, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
