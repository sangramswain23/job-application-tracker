package com.jobtracker.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobtracker.application.entity.JobApplication;
import com.jobtracker.application.entity.User;
import com.jobtracker.application.enums.ApplicationStatus;
import com.jobtracker.application.repository.JobApplicationRepository;
import com.jobtracker.application.repository.UserRepository;
import com.jobtracker.application.service.JobApplicationService;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationServiceImpl(
            JobApplicationRepository jobApplicationRepository,
            UserRepository userRepository
    ) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JobApplication createApplication(Long userId, String companyName, String role) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        JobApplication application = new JobApplication();
        application.setCompanyName(companyName);
        application.setRole(role);
        application.setUser(user);

        return jobApplicationRepository.save(application);
    }

    @Override
    public JobApplication updateStatus(Long applicationId, ApplicationStatus newStatus) {

        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        application.addStatus(newStatus); // DOMAIN ENFORCEMENT

        return jobApplicationRepository.save(application);
    }

    @Override
    public List<JobApplication> getApplicationsForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return jobApplicationRepository.findByUser(user);
    }

    @Override
    public List<JobApplication> getApplicationsByStatus(Long userId, ApplicationStatus status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return jobApplicationRepository.findByUserAndStatus(user, status);
    }
}
