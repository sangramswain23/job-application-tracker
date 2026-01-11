package com.jobtracker.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.application.entity.JobApplication;
import com.jobtracker.application.entity.User;
import com.jobtracker.application.enums.ApplicationStatus;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{

	List<JobApplication> findByUser(User user);
	List<JobApplication> findByUserAndStatus(User user, ApplicationStatus status);
}
