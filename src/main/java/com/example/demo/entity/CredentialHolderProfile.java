package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "credential_holder_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String holderId;

    @Column(unique = true)
    private String email;

    private String fullName;

    private String organization;

    @Builder.Default
    private Boolean active = true;
}
