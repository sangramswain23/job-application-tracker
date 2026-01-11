package com.jobtracker.application.enums;

import java.util.EnumSet;
import java.util.Set;

public enum ApplicationStatus {

    APPLIED,
    SHORTLISTED,
    INTERVIEW,
    OFFERED,
    REJECTED;

    private Set<ApplicationStatus> nextAllowedStatuses;

    static {
        APPLIED.nextAllowedStatuses = EnumSet.of(SHORTLISTED, REJECTED);
        SHORTLISTED.nextAllowedStatuses = EnumSet.of(INTERVIEW, REJECTED);
        INTERVIEW.nextAllowedStatuses = EnumSet.of(OFFERED, REJECTED);
        OFFERED.nextAllowedStatuses = EnumSet.noneOf(ApplicationStatus.class);
        REJECTED.nextAllowedStatuses = EnumSet.noneOf(ApplicationStatus.class);
    }

    public boolean canTransitionTo(ApplicationStatus nextStatus) {
        return nextAllowedStatuses.contains(nextStatus);
    }
}
