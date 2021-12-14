package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;

import java.util.List;

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
}
