package com.jobtracker.application.dto;

import com.jobtracker.application.enums.ApplicationStatus;

import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull
    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
