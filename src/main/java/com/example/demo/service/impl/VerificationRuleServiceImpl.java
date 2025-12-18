package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository ruleRepo;

    @Override
    public VerificationRule createRule(VerificationRule rule) {

        if(rule.getRuleCode() == null || rule.getRuleCode().isBlank()) {
            throw new BadRequestException("ruleCode cannot be empty");
        }

        if(ruleRepo.existsByRuleCode(rule.getRuleCode())) {
            throw new BadRequestException("ruleCode must be unique");
        }

        return ruleRepo.save(rule);
    }
}
