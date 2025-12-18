package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialHolderProfileServiceImpl
        implements CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repo;

    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile){
        profile.setCreatedAt(LocalDateTime.now());
        return repo.save(profile);
    }

    @Override
    public CredentialHolderProfile getHolderById(Long id){
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Holder not found"));
    }

    @Override
    public List<CredentialHolderProfile> getAllHolders(){
        return repo.findAll();
    }

    @Override
    public boolean existsHolderByHolderId(String holderId){
        return repo.findByHolderId(holderId).isPresent();
    }

    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active){
        CredentialHolderProfile holder = getHolderById(id);
        holder.setActive(active);
        return repo.save(holder);
    }
}
