package com.jobtracker.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobtracker.application.enums.ApplicationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_applications")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String companyName;
	
	@Column(nullable = false)
	private String role;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationStatus status;
	
	@Column(nullable = false)
	private LocalDateTime appliedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "jobApplication",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ApplicationStatusHistory> statusHistory=new ArrayList<>();
	
	@PrePersist
	protected void onCreate() {
		this.appliedAt=LocalDateTime.now();
		addStatus(ApplicationStatus.APPLIED);
	}
	
	public void addStatus(ApplicationStatus newStatus) {
		 if (this.status != null && !this.status.canTransitionTo(newStatus)) {
		        throw new IllegalStateException(
		            "Invalid status transition from " + this.status + " to " + newStatus
		        );
		    }
		this.status=newStatus;
		
		ApplicationStatusHistory history=new ApplicationStatusHistory();
		history.setStatus(newStatus);
		history.setJobApplication(this);
		
		this.statusHistory.add(history);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
