package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
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
