package com.seman.projhandle.processor;

import java.util.Arrays;
import java.util.List;

public class EligibleActivitiesProcessor extends Processor {
    private static final List<String> keyWords = Arrays.asList(
            "Activitati eligibile:",
            "Activitati eligibile"
    );

    @Override
    public String getPropertyValue(String project) {
        String rawActivities = searchKeywordsInProjectString(project, keyWords);
        if (rawActivities.indexOf(':') > 0) {
            rawActivities = rawActivities.split(":")[1];
        }
        rawActivities = rawActivities.trim();
        return rawActivities;
    }
}
