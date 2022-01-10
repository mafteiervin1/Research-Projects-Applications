package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public abstract class Processor {
    abstract String getPropertyValue(String project);

    public String searchKeywordsInProjectString(String project, List <String> keyWords) {
        StringBuilder fieldValue = new StringBuilder();
        for (String keyword : keyWords) {
            if (project.contains(keyword)) {
                log.info("Keyword [{}] found for [{}]", keyword, super.getClass().getName());
                String field = project.substring(project.indexOf(keyword) + keyword.length()).trim();
                String[] fieldLines = field.split("\n");
                for (String line : fieldLines) {
                    if (!line.isEmpty()) {
                        fieldValue.append(line);
                        fieldValue.append("\n");
                    } else {
                        return fieldValue.substring(0, fieldValue.toString().lastIndexOf("\n"));
                    }
                }
            }
        }
        return fieldValue.toString();
    }

    public String matchRegexInProjectString(String project, List <String> patterns) {
        Pattern compiler;
        ArrayList <String> keywordsFound = new ArrayList <>();
        for (String pattern: patterns) {
            compiler = Pattern.compile(pattern);
            Matcher matcher = compiler.matcher(project);
            while(matcher.find()){
                log.info("regex \"{}\" match value {}", pattern, matcher.group());
                keywordsFound.add(matcher.group());
            }
        }
        return searchKeywordsInProjectString(project, keywordsFound);
    }
}
