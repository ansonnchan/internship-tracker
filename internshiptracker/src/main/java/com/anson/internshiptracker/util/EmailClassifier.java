package com.anson.internshiptracker.util;

import com.anson.internshiptracker.model.ApplicationStatus;

public class EmailClassifier {
    
    public static ApplicationStatus classify(String emailSubject, String emailBody) {
        
        // Combine emailSubject and emailBody
        String combinedText = (emailSubject + " " + emailBody).toLowerCase();
        
        // Check for offer/accepted keywords
        if (containsAny(combinedText, 
            "offer", "congratulations", "pleased", "excited", "accepted",
            "job offer", "hired", "successful")) {
            return ApplicationStatus.OFFER;
        }
        
        // Check for interview keywords
        if (containsAny(combinedText, 
            "interview", "schedule a call", "next steps", "phone screen",
            "meet with", "talk with you", "schedule a time", "video call")) {
            return ApplicationStatus.INTERVIEW;
        }
        
        // Check for rejection keywords
        if (containsAny(combinedText,
            "unfortunately", "not moving forward", "other candidates", 
            "we regret", "decided to pursue", "not selected", "will not be moving forward",
            "chose to move forward with other", "not a fit at this time")) {
            return ApplicationStatus.REJECTED;
        }
        
        return ApplicationStatus.OTHER;
    }
    
    // Helper method to check if text contains any of the keywords
    private static boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
