package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Log4j2
@Component
public class ActivePeriodProcessor extends Processor {
    private static final List<String> keyWords = Arrays.asList(
            "Termen limita:",
            "Termen limita",
            "Termen limită:",
            "Termen limită"
    );

    private static List <String> patterns = Arrays.asList(
        "(?i)termen.*( limit.*(:)?)?\\s*"
    );

    @Override
    public String getPropertyValue(String project) {
        String matchedRegex = matchRegexInProjectString(project, patterns);
        if (!matchedRegex.isEmpty()) {
            return matchedRegex;
        }
        return searchKeywordsInProjectString(project, keyWords);
    }
}