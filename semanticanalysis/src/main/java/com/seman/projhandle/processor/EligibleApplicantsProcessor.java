package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class EligibleApplicantsProcessor extends Processor {
    private static List <String> keyWords = Arrays.asList(
        "Categorii solicitanti eligibili:",
        "Categorii solicitanti eligibili");

    private static List<String> patterns = Arrays.asList(
        "(?i)Categori.*( solicitant.*(:)?)? .*( eligibil.*(:)?)?\\s*"
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
