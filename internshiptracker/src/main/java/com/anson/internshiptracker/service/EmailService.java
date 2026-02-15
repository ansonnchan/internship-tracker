package com.anson.internshiptracker.service;

import com.anson.internshiptracker.util.EmailClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private ApplicationService applicationService;

    //classify by email and update application status
    public String classifyAndUpdate(Long applicationId, String emailSubject, String emailBody) {
        //use emailclassifier to get status
        String status = EmailClassifier.classify(emailSubject, emailBody);
        
        //update application
        applicationService.updateStatus(applicationId, status);

        return status;
    }

    //subject only 
    public String classifyAndUpdate(Long applicationId, String emailSubject) {
        return classifyAndUpdate(applicationId, emailSubject, "");
    }

    //classify w/o updating
    public String classifyOnly(String emailSubject, String emailBody) {
        return EmailClassifier.classify(emailSubject, emailBody); 
    }
}
