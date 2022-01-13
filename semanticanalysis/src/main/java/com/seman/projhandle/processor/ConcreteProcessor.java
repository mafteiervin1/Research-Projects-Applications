package com.seman.projhandle.processor;

import lombok.Data;

import java.util.List;

@Data
public class ConcreteProcessor extends Processor {
    private List <String> keyWords;
    private List <String> regex;

    public ConcreteProcessor(List<String> keyWords, List<String> regex) {
        this.keyWords = keyWords;
        this.regex = regex;
    }

    @Override
    public String getPropertyValue(String project) {
        return searchKeywordsInProjectString(project, keyWords);
    }
}
