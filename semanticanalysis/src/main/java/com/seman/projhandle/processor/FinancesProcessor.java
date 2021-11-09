package com.seman.projhandle.processor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FinancesProcessor extends Processor {
    private static final List<String> keyWords1 = Arrays.asList(
            "Finantator:",
            "Finantator",
            "Finanțator:",
            "Finanțator"
    );
    private static final List<String> keyWords2 = Arrays.asList(
            "Finantare primita (%):",
            "Finantare primita (%)",
            "Finanțare primită (%):",
            "Finanțare primită (%)"
    );

    @Override
    public String getPropertyValue(String project) {
        String organization = searchKeywordsInProjectString(project, keyWords1);
        String percentage = searchKeywordsInProjectString(project, keyWords2);

        if (Objects.equals(organization, "") || Objects.equals(percentage, "")) {
            return "";
        }

        return organization + "\nFinantare primita: " + percentage;
    }
}
