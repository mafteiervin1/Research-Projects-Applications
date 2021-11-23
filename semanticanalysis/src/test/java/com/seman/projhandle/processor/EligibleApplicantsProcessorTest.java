package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
class EligibleApplicantsProcessorTest {

    private Processor eligibleApplicantsProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        eligibleApplicantsProcessor = new EligibleApplicantsProcessor();
    }

    @Test
    void testGetPropertyValueReturnEligibleApplicants() {
        projectTestData = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("singleProjectTestDataWithAllProperties.txt"));
        String expectedResult = "Unități administrative teritoriale de tipul comunelor și orașelor (inclusiv municipii)\nOrganizațiile neguvernamentale";
        assertEquals(expectedResult, eligibleApplicantsProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(this.getClass().getResourceAsStream("singleProjectTestDataWithoutEligibleApplicants.txt"));
        assertEquals("", eligibleApplicantsProcessor.getPropertyValue(projectTestData));
    }
}