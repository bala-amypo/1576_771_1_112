package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_rules")
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique  = true)
    private String ruleCode;

    private String description;

    private String appliesToType;

    private String validationExpression;

    private boolean active;

    public VerificationRule() {}

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public String getDescription() { return description; }
    public String getAppliesToType() { return appliesToType; }
    public String getValidationExpression() { return validationExpression; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setDescription(String description) { this.description = description; }
    public void setAppliesToType(String appliesToType) { this.appliesToType = appliesToType; }
    public void setValidationExpression(String validationExpression) { this.validationExpression = validationExpression; }
    public void setActive(boolean active) { this.active = active; }
}
