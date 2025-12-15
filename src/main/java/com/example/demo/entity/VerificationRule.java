package com.example.demo.entity;

public class VerificationRule {
    private long id;
    private String ruleCode;
    private String description;
    private String appliesToType;
    private String validationExpression;
    private boolean active;

    public VerificationRule(){}

    public VerificationRule(String ruleCode, String description, String appliesToType, String validationExpression,
            boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.appliesToType = appliesToType;
        this.validationExpression = validationExpression;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public String getAppliesToType() {
        return appliesToType;
    }

    public String getValidationExpression() {
        return validationExpression;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppliesToType(String appliesToType) {
        this.appliesToType = appliesToType;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    

}
