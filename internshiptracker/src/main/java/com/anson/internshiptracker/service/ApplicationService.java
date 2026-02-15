package com.anson.internshiptracker.service;

import com.anson.internshiptracker.model.Application;
import com.anson.internshiptracker.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationRepository applicationRepository;

    //create new application
    public Application createApplication (Application application) {
        return applicationRepository.save(application);
    }

    //get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    //get application by ID
    public Optional<Application> getApplicationbyId(Long id) {
        return applicationRepository.findById(id);
    }

    //update application
    public Application updateApplication(Long id, Application updatedApp) {
        return applicationRepository.findById(id)
            .map(app -> { 
                app.setCompany(updatedApp.getCompany());
                app.setPosition(updatedApp.getPosition());
                app.setStatus(updatedApp.getStatus());
                app.setDateApplied(updatedApp.getDateApplied());
                return applicationRepository.save(app);
            }).orElseThrow(() -> new RuntimeException("Application not found")); 
    }

    //delete application
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    //update status only 
    public Application updateStatus(Long id, String status) {
    return applicationRepository.findById(id)
        .map(app -> {
            app.setStatus(status);
            return applicationRepository.save(app);
        }).orElseThrow(() -> new RuntimeException("Application not found"));
}
}
