package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationRuleServiceImpl
        implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return repository.save(rule);
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        VerificationRule rule = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
        rule.setRuleCode(updatedRule.getRuleCode());
        rule.setActive(updatedRule.getActive());
        return repository.save(rule);
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return repository.findAll();
    }

    public VerificationRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
    }
}
