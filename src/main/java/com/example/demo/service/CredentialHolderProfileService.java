package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CredentialHolderProfile;

public interface CredentialHolderProfileService {
    CredentialHolderProfile createHolder(CredentialHolderProfile profile);
    CredentialHolderProfile getHolderById(Long id);
    List<CredentialHolderProfile> getAllHolders();
    boolean findByHolderId(String holderId);
    CredentialHolderProfile updateHolderStatus(Long id, boolean active);
}
