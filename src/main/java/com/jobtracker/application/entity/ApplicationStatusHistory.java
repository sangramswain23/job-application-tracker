package com.jobtracker.application.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import com.jobtracker.application.enums.ApplicationStatus;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_status_history")
public class ApplicationStatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationStatus status;
	
	@Column(nullable = false)
	private LocalDateTime changedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_application_id",nullable = false)
	private JobApplication jobApplication;
	
	@PrePersist
	protected void onCreate() {
		this.changedAt=LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}
}
