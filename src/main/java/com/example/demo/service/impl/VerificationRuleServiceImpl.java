package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationRuleServiceImpl
        implements VerificationRuleService {

    private final VerificationRuleRepository ruleRepo;

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return ruleRepo.save(rule);
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule rule) {

        VerificationRule existing =
                ruleRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Rule not found"));

        existing.setRuleCode(rule.getRuleCode());
        existing.setActive(rule.isActive());

        return ruleRepo.save(existing);
    }

    @Override
    public Optional<VerificationRule> getRuleById(Long id) {
        return ruleRepo.findById(id);
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
