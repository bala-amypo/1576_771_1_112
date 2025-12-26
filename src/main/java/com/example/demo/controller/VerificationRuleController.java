package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class VerificationRuleController {

    private final VerificationRuleService service;

    @PostMapping
    public ResponseEntity<VerificationRule> create(
            @RequestBody VerificationRule rule) {

        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<VerificationRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<VerificationRule>> getActiveRules() {
        return ResponseEntity.ok(service.getActiveRules());
    }
}
