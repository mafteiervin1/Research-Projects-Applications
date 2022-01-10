package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class MoreInfoProcessor extends Processor {
    private static List <String> keyWords = Arrays.asList(
        "Informatii suplimentare:",
        "Informatii suplimentare");

    @Override
    public String getPropertyValue(String project) {
        return searchKeywordsInProjectString(project, keyWords);
    }
}
