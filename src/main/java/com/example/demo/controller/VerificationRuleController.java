package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Verification Rules", description = "Verification rule management endpoints")
public class VerificationRuleController {
    
    private final VerificationRuleService ruleService;
    
    public VerificationRuleController(VerificationRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    @Operation(summary = "Create rule")
    public ResponseEntity<VerificationRule> create(@RequestBody VerificationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update rule")
    public ResponseEntity<VerificationRule> update(@PathVariable Long id, @RequestBody VerificationRule updatedRule) {
        return ResponseEntity.ok(ruleService.updateRule(id, updatedRule));
    }
    
    @GetMapping("/active")
    @Operation(summary = "List active rules")
    public ResponseEntity<List<VerificationRule>> getActive() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get rule by ID")
    public ResponseEntity<VerificationRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getAllRules().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null));
    }
    
    @GetMapping
    @Operation(summary = "List all rules")
    public ResponseEntity<List<VerificationRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}