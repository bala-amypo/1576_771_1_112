package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class VerificationRuleController {

    private final VerificationRuleService ruleService;

    @PostMapping
    public ResponseEntity<VerificationRule> create(
            @RequestBody VerificationRule rule) {

        return ResponseEntity.status(201)
                .body(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VerificationRule> update(
            @PathVariable Long id,
            @RequestBody VerificationRule rule) {

        return ResponseEntity.ok(
                ruleService.updateRule(id, rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VerificationRule>> getActiveRules() {
        return ResponseEntity.ok(
                ruleService.getActiveRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationRule> getById(
            @PathVariable Long id) {

        Optional<VerificationRule> rule =
                ruleService.getRuleById(id);

        return rule.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VerificationRule>> getAll() {
        return ResponseEntity.ok(
                ruleService.getAllRules());
    }
}
