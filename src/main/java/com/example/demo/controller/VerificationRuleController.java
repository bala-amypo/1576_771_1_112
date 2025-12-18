package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class VerificationRuleController {

    private final VerificationRuleService service;

    @PostMapping
    public ResponseEntity<VerificationRule> create(@RequestBody VerificationRule rule){
        return ResponseEntity.status(201).body(service.createRule(rule));
    }
}
