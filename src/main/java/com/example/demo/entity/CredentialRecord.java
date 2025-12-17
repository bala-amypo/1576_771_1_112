// package com.example.demo.entity;

// import java.time.LocalDate;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Column;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class CredentialRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;
//     private long holderId;
//     @Column(unique = true)
//     private String credentialCode;
//     private String title;
//     private String issuer;
//     private LocalDate issuedDate;
//     private LocalDate expiryDate;
//     private String credentialType;
//     private String status;
//     private String metadataJson;

//     public CredentialRecord() {}

//     public CredentialRecord(String credentialCode, String credentialType, LocalDate expiryDate, long holderId, LocalDate issuedDate, String issuer, String metadataJson, String status, String title) {
//         this.credentialCode = credentialCode;
//         this.credentialType = credentialType;
//         this.expiryDate = expiryDate;
//         this.holderId = holderId;
//         this.issuedDate = issuedDate;
//         this.issuer = issuer;
//         this.metadataJson = metadataJson;
//         this.status = status;
//         this.title = title;
//     }

//     public long getId() {
//         return id;
//     }

//     public long getHolderId() {
//         return holderId;
//     }

//     public String getCredentialCode() {
//         return credentialCode;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public String getIssuer() {
//         return issuer;
//     }

//     public LocalDate getIssuedDate() {
//         return issuedDate;
//     }

//     public LocalDate getExpiryDate() {
//         return expiryDate;
//     }

//     public String getCredentialType() {
//         return credentialType;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public String getMetadataJson() {
//         return metadataJson;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public void setHolderId(long holderId) {
//         this.holderId = holderId;
//     }

//     public void setCredentialCode(String credentialCode) {
//         this.credentialCode = credentialCode;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public void setIssuer(String issuer) {
//         this.issuer = issuer;
//     }

//     public void setIssuedDate(LocalDate issuedDate) {
//         this.issuedDate = issuedDate;
//     }

//     public void setExpiryDate(LocalDate expiryDate) {
//         this.expiryDate = expiryDate;
//     }

//     public void setCredentialType(String credentialType) {
//         this.credentialType = credentialType;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public void setMetadataJson(String metadataJson) {
//         this.metadataJson = metadataJson;
//     }


// }
