// package com.example.demo.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.PrePersist;

// @Entity
// public class AuditTrailRecord {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;
//     private long credentialId;
//     private String eventType;
//     private String details;
//     private LocalDateTime loggedAt;

    
//     @PrePersist
//     protected void onCreate() {
//         loggedAt = LocalDateTime.now();
//     }

//     public AuditTrailRecord() {}
    
//     public AuditTrailRecord(long credentialId, String details, String eventType, LocalDateTime loggedAt) {
//         this.credentialId = credentialId;
//         this.details = details;
//         this.eventType = eventType;
//         this.loggedAt = loggedAt;
//     }

//     public long getId() {
//         return id;
//     }

//     public long getCredentialId() {
//         return credentialId;
//     }

//     public String getEventType() {
//         return eventType;
//     }

//     public String getDetails() {
//         return details;
//     }

//     public LocalDateTime getLoggedAt() {
//         return loggedAt;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public void setCredentialId(long credentialId) {
//         this.credentialId = credentialId;
//     }

//     public void setEventType(String eventType) {
//         this.eventType = eventType;
//     }

//     public void setDetails(String details) {
//         this.details = details;
//     }

//     public void setLoggedAt(LocalDateTime loggedAt) {
//         this.loggedAt = loggedAt;
//     }

// }