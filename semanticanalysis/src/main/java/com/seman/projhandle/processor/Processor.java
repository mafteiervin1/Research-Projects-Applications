package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public abstract class Processor {
    abstract String getPropertyValue(String project);

    String searchKeywordsInProjectString(String project, List <String> keyWords) {
        for (String keyword : keyWords) {
            if(project.contains(keyword)) {
                log.info("Keyword [{}] found for [{}]", keyword, super.getClass().getName());
                String field = project.substring(project.indexOf(keyword) + keyword.length()).trim();
                StringBuilder fieldValue = new StringBuilder();
                String[] fieldLines = field.split(System.lineSeparator());
                for (String line : fieldLines) {
                    if (!line.isEmpty()) {
                        fieldValue.append(line);
                        fieldValue.append(System.lineSeparator());
                    } else {
                        return fieldValue.toString().substring(0, fieldValue.toString().lastIndexOf(System.lineSeparator()));
                    }
                }
            }
        }
        return "";
    }
}
