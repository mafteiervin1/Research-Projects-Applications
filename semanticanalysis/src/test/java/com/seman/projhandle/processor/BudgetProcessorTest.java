package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BudgetProcessorTest {

    private Processor budgetProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        budgetProcessor = new BudgetProcessor();
    }

    @Test
    void testGetPropertyValueReturnBudget() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties.txt")
        );
        String expectedResult = "1.647.059 Euro";
        assertEquals(expectedResult, budgetProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getResourceAsStream("singleProjectTestDataWithoutBudget.txt"));
        assertEquals("", budgetProcessor.getPropertyValue(projectTestData));
    }
}
