package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class FinancesProcessor extends Processor {
    private static final List <String> keyWords = Arrays.asList(
        "Finantator:",
        "Finantator",
        "Finanțator:",
        "Finanțator",
        "Finantare primita (%):",
        "Finantare primita (%)",
        "Finanțare primită (%):",
        "Finanțare primită (%)"
    );

    private static List <String> patterns = Arrays.asList(
        "(?i)finanțat.*(:)?\\s*",
        "(?i)finanțar.*( primit.*(:)?)?\\s*"
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
