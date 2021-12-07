package com.seman.projhandle;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProjectDetails {
    private String activePeriod;
    private String domain;
    private String eligibleApplicants;
    private String purpose;
    private String moreInfo;
    private String budget;
    private String eligibleActivities;
    private String finances;
}
