package com.seman.projhandle;

import com.seman.projhandle.processor.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProjectHandler {

    @Autowired
    ActivePeriodProcessor activePeriodProcessor;

    @Autowired
    DomainProcessor domainProcessor;

    @Autowired
    EligibleApplicantsProcessor eligibleApplicantsProcessor;

    @Autowired
    PurposeProcessor purposeProcessor;

    @Autowired
    MoreInfoProcessor moreInfoProcessor;

    @Autowired
    BudgetProcessor budgetProcessor;

    @Autowired
    EligibleActivitiesProcessor eligibleActivitiesProcessor;

    @Autowired
    FinancesProcessor financesProcessor;

    public ProjectDetails getProjectDetails(String project) {
        return ProjectDetails.builder()
                .activePeriod(activePeriodProcessor.getPropertyValue(project))
                .domain(domainProcessor.getPropertyValue(project))
                .purpose(purposeProcessor.getPropertyValue(project))
                .eligibleApplicants(eligibleApplicantsProcessor.getPropertyValue(project))
                .moreInfo(moreInfoProcessor.getPropertyValue(project))
                .budget(budgetProcessor.getPropertyValue(project))
                .eligibleActivities(eligibleActivitiesProcessor.getPropertyValue(project))
                .finances(financesProcessor.getPropertyValue(project))
                .build();
    }
}
