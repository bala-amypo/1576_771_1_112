package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_rules")
public class VerificationRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "rule_code", unique = true)
    private String ruleCode;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "applies_to_type")
    private String appliesToType;
    
    @Column(name = "validation_expression", columnDefinition = "TEXT")
    private String validationExpression;
    
    @Column(nullable = false)
    private Boolean active;
    
    public VerificationRule() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppliesToType() {
        return appliesToType;
    }

    public void setAppliesToType(String appliesToType) {
        this.appliesToType = appliesToType;
    }

    public String getValidationExpression() {
        return validationExpression;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}