package com.jobtracker.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jobtracker.application.dto.CreateJobApplicationRequest;
import com.jobtracker.application.dto.UpdateStatusRequest;
import com.jobtracker.application.entity.JobApplication;
import com.jobtracker.application.enums.ApplicationStatus;
import com.jobtracker.application.service.JobApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@Validated
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplication> createApplication(
            @Valid @RequestBody CreateJobApplicationRequest request
    ) {
        JobApplication application = jobApplicationService.createApplication(
                request.getUserId(),
                request.getCompanyName(),
                request.getRole()
        );
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        JobApplication application = jobApplicationService.updateStatus(
                id,
                request.getStatus()
        );
        return ResponseEntity.ok(application);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplication>> getUserApplications(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                jobApplicationService.getApplicationsForUser(userId)
        );
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<JobApplication>> getByStatus(
            @PathVariable Long userId,
            @PathVariable ApplicationStatus status
    ) {
        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByStatus(userId, status)
        );
    }
}
