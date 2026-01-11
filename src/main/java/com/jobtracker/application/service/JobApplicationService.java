package com.jobtracker.application.service;

import java.util.List;

import com.jobtracker.application.entity.JobApplication;
import com.jobtracker.application.enums.ApplicationStatus;

public interface JobApplicationService {

	JobApplication createApplication(
			Long userId,
			String companyName,
			String role
			);
	
	JobApplication updateStatus(
			Long applicationId,
			ApplicationStatus newStatus
			);
	
	List<JobApplication> getApplicationsForUser(Long userId);
	List<JobApplication> getApplicationsByStatus(
			Long userId,
			ApplicationStatus status
			);
}
