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

    private String email;

    private String organization;

    private Boolean active = true;
}
