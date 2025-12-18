package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "VIEWER";

    public User(){}

    public User(String fullName, String email, String password, String role){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        if (!role.equals("ADMIN") && !role.equals("EDITOR") && !role.equals("VIEWER")) {
            role = "VIEWER";
        }
    }

    public Long getId(){ return id; }
    public String getFullName(){ return fullName; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public String getRole(){ return role; }

    public void setId(Long id){ this.id=id; }
    public void setFullName(String fullName){ this.fullName=fullName; }
    public void setEmail(String email){ this.email=email; }
    public void setPassword(String password){ this.password=password; }
    public void setRole(String role){ this.role=role; }
}
