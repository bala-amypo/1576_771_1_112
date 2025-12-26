package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_trail_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private String action;
    private LocalDateTime loggedAt;
}
