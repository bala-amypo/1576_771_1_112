package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_rules")
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    private boolean active;

    public VerificationRule() {}

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setActive(boolean active) { this.active = active; }
}
