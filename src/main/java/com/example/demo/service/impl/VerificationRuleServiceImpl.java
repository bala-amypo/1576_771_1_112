package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}
