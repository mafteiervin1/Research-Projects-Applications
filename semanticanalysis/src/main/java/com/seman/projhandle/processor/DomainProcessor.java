package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class DomainProcessor extends Processor {
    private static final List <String> keyWords = Arrays.asList(
        "Domeniu:",
        "Domeniu",
        "Domeniu de activitate",
        "Domeniu de activitate:"
    );

    private static List<String> patterns = Arrays.asList(
        "(?i)Domeni.*( activitat.*(:)?)?\\s*"
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
