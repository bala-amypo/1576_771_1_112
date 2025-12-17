// package com.example.demo.entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Column;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class VerificationRule {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;
//     @Column(unique = true)
//     private String ruleCode;
//     private String description;
//     private String appliesToType;
//     private String validationExpression;
//     private boolean active;

//     public VerificationRule() {}

//     public VerificationRule(boolean active, String appliesToType, String description, String ruleCode, String validationExpression) {
//         this.active = active;
//         this.appliesToType = appliesToType;
//         this.description = description;
//         this.ruleCode = ruleCode;
//         this.validationExpression = validationExpression;
//     }

//     public long getId() {
//         return id;
//     }

//     public String getRuleCode() {
//         return ruleCode;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public String getAppliesToType() {
//         return appliesToType;
//     }

//     public String getValidationExpression() {
//         return validationExpression;
//     }

//     public boolean isActive() {
//         return active;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public void setRuleCode(String ruleCode) {
//         this.ruleCode = ruleCode;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public void setAppliesToType(String appliesToType) {
//         this.appliesToType = appliesToType;
//     }

//     public void setValidationExpression(String validationExpression) {
//         this.validationExpression = validationExpression;
//     }

//     public void setActive(boolean active) {
//         this.active = active;
//     }


// }
