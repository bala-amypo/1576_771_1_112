package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.AuditTrailService;
import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    @Autowired
	AuditTrailRecordRepository auditRepository;

	@Override
	public AuditTrailRecord logEvent(AuditTrailRecord record) {
		return auditRepository.save(record);
	}

	@Override
	public Optional<AuditTrailRecord> getLogsByCredential(Long credentialId) {
		return auditRepository.findById(credentialId);
	}

	@Override
	public List<AuditTrailRecord> getAllLogs() {
		return auditRepository.findAll();
	}

}
