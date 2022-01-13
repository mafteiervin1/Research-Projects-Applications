package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class BudgetProcessor extends Processor {
    private static List <String> keyWords = Arrays.asList(
        "Buget apel",
        "Buget apel:",
        "Buget",
        "Buget:",
        "Fonduri",
        "Fonduri:",
        "Fonduri alocate",
        "Fonduri alocate:"
    );

    private static List <String> patterns = Arrays.asList(
        "(?i)Buget.*(:)?\\s*",
        "(?i)Buget.*( apel.*(:)?)?\\s*",
        "(?i)Fonduri.*(:)?\\s*",
        "(?i)Fonduri.*( (apel|alocate).*(:)?)?\\s*"
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
