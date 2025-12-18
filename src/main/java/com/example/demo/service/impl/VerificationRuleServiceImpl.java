package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository ruleRepo;

    @Override
    public VerificationRule createRule(VerificationRule rule) {

        if (rule.getRuleCode() == null || rule.getRuleCode().isBlank())
            throw new BadRequestException("ruleCode required");

        if (ruleRepo.existsByRuleCode(rule.getRuleCode()))
            throw new BadRequestException("ruleCode must be unique");

        return ruleRepo.save(rule);
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule update) {

        VerificationRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        existing.setRuleCode(update.getRuleCode());
        existing.setDescription(update.getDescription());
        existing.setAppliesToType(update.getAppliesToType());
        existing.setValidationExpression(update.getValidationExpression());
        existing.setActive(update.isActive());

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
