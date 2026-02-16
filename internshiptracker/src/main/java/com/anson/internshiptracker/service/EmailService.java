package com.anson.internshiptracker.service;

import com.anson.internshiptracker.model.ApplicationStatus;
import com.anson.internshiptracker.util.EmailClassifier;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private final ApplicationService applicationService;
    
    public EmailService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    
    // Classify email and update application status
    public ApplicationStatus classifyAndUpdate(Long applicationId, String emailSubject, String emailBody, Long userId) {
        // Use EmailClassifier to get status (returns ApplicationStatus enum)
        ApplicationStatus status = EmailClassifier.classify(emailSubject, emailBody);
        
        // Update application with the classified status
        applicationService.updateStatus(applicationId, status, userId);
        
        return status;
    }

    public ApplicationStatus classifyAndUpdate(Long applicationId, String emailSubject, Long userId) {
        return classifyAndUpdate(applicationId, emailSubject, "", userId);
    }
    
    // Classify without updating 
    public ApplicationStatus classifyOnly(String emailSubject, String emailBody) {
        return EmailClassifier.classify(emailSubject, emailBody);
    }
}
