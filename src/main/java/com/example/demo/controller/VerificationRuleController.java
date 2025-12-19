package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class VerificationRuleController {

    private final VerificationRuleService ruleService;

    @PostMapping
    public ResponseEntity<VerificationRule> create(@Valid @RequestBody VerificationRule rule) {
        VerificationRule created = ruleService.createRule(rule);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VerificationRule> update(@PathVariable Long id,@Valid @RequestBody VerificationRule rule) {
        VerificationRule updated = ruleService.updateRule(id, rule);
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("/active")
    public ResponseEntity<List<VerificationRule>> getActiveRules() {
        List<VerificationRule> rules = ruleService.getActiveRules();
        return ResponseEntity.status(200).body(rules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationRule> getById(@PathVariable Long id) {
        Optional<VerificationRule> rule = ruleService.getRuleById(id);
        if (rule.isPresent()) {
            return ResponseEntity.status(200).body(rule.get());
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<VerificationRule>> getAll() {
        List<VerificationRule> rules = ruleService.getAllRules();
        return ResponseEntity.status(200).body(rules);
    }
}
