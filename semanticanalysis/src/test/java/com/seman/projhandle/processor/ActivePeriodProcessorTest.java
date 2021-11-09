package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivePeriodProcessorTest {

    private Processor activePeriodProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        activePeriodProcessor = new ActivePeriodProcessor();
    }

    @Test
    void testGetPropertyValueReturnActivePeriod() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties.txt")
        );
        String expectedResult = "15 noiembrie 2021";
        assertEquals(expectedResult, activePeriodProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getResourceAsStream("singleProjectTestDataWithoutActivePeriod.txt"));
        assertEquals("", activePeriodProcessor.getPropertyValue(projectTestData));
    }
}
