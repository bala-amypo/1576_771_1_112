package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {
    
    private final VerificationRuleRepository ruleRepo;
    
    public VerificationRuleServiceImpl(VerificationRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }
    
    @Override
    public VerificationRule createRule(VerificationRule rule) {
        try {
            if (rule == null) {
                rule = new VerificationRule();
            }
            return ruleRepo.save(rule);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create rule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        try {
            if (id == null) {
                throw new ResourceNotFoundException("Rule ID cannot be null");
            }
            
            if (updatedRule == null) {
                throw new IllegalArgumentException("Updated rule cannot be null");
            }
            
            VerificationRule existing = ruleRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
            
            if (updatedRule.getRuleCode() != null) {
                existing.setRuleCode(updatedRule.getRuleCode());
            }
            if (updatedRule.getDescription() != null) {
                existing.setDescription(updatedRule.getDescription());
            }
            if (updatedRule.getAppliesToType() != null) {
                existing.setAppliesToType(updatedRule.getAppliesToType());
            }
            if (updatedRule.getValidationExpression() != null) {
                existing.setValidationExpression(updatedRule.getValidationExpression());
            }
            if (updatedRule.getActive() != null) {
                existing.setActive(updatedRule.getActive());
            }
            
            return ruleRepo.save(existing);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update rule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<VerificationRule> getActiveRules() {
        try {
            return ruleRepo.findByActiveTrue();
        } catch (Exception e) {
            return List.of();
        }
    }
    
    @Override
    public List<VerificationRule> getAllRules() {
        try {
            return ruleRepo.findAll();
        } catch (Exception e) {
            return List.of();
        }
    }
}