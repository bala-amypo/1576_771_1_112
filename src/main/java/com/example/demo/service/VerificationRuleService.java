package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.VerificationRule;

public interface VerificationRuleService {

    VerificationRule createRule(VerificationRule rule);

    VerificationRule updateRule(Long id, VerificationRule rule);

    Optional<VerificationRule> getRuleById(Long id);

    List<VerificationRule> getActiveRules();

    List<VerificationRule> getAllRules();
}
