package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import java.util.List;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord record);

    CredentialRecord updateCredential(Long id, CredentialRecord record);

    List<CredentialRecord> getCredentialsByHolder(Long holderId);

    CredentialRecord getByCredentialCode(String credentialCode);

    List<CredentialRecord> getAllCredentials();
}
