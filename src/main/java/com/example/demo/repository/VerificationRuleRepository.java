package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;

import java.util.*;

public interface VerificationRuleRepository
        extends JpaRepository<VerificationRule, Long> {
    List<VerificationRule> findByActiveTrue();
}

