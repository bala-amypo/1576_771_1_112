package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface AuditTrailService {
    AuditTrailRecord logEvent(AuditTrailRecord record);
    List<AuditTrailRecord> getLogsByCredential(Long credentialId);
}
