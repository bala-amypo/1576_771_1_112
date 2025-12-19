package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleRepository ruleRepo;

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {

        if(record.getCredentialCode() == null)
            throw new BadRequestException("credentialCode required");

        if(credentialRepo.findByCredentialCode(record.getCredentialCode()).isPresent())
            throw new BadRequestException("credentialCode must be unique");

        if(record.getExpiryDate() != null && record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        }
        else {
            if(record.getStatus() == null)
                record.setStatus("VALID");
        }

        if(record.getMetadataJson() != null && !record.getMetadataJson().trim().startsWith("{"))
            throw new BadRequestException("metadataJson must start with '{'");

        if(record.getRules() != null){
            Set<VerificationRule> dbRules = record.getRules().stream()
                    .map(r -> {

                        VerificationRule rule = ruleRepo.findById(r.getId())
                                .orElseThrow(() -> new BadRequestException("Rule not found"));

                        if(!rule.isActive())
                            throw new BadRequestException("Cannot attach inactive rules");

                        return rule;

                    }).collect(Collectors.toSet());

            record.setRules(dbRules);
        }

        return credentialRepo.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord update) {

        CredentialRecord existing = credentialRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        existing.setCredentialCode(update.getCredentialCode());
        existing.setTitle(update.getTitle());
        existing.setIssuer(update.getIssuer());
        existing.setCredentialType(update.getCredentialType());
        existing.setExpiryDate(update.getExpiryDate());
        existing.setMetadataJson(update.getMetadataJson());

        if(update.getExpiryDate() != null &&
           update.getExpiryDate().isBefore(LocalDate.now())) {
            existing.setStatus("EXPIRED");
        }

        if(update.getRules() != null){
            Set<VerificationRule> attached =
                    update.getRules().stream().map(r -> {

                        VerificationRule dbRule = ruleRepo.findById(r.getId())
                                .orElseThrow(() -> new BadRequestException("Rule not found"));

                        if(!dbRule.isActive())
                            throw new BadRequestException("Cannot attach inactive rules");

                        return dbRule;
                    }).collect(Collectors.toSet());

            existing.setRules(attached);
        }

        return credentialRepo.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId){
        return credentialRepo.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code){
        return credentialRepo.findByCredentialCode(code).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials(){
        return credentialRepo.findAll();
    }
}
