package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurposeProcessorTest {

    private Processor purposeProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        purposeProcessor = new PurposeProcessor();
    }

    @Test
    void testGetPropertyValueReturnPurpose() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties.txt")
        );
        String expectedResult = "Imbunătățirea condițiilor de viață și de educație în comunitățile dezavantajate\r\n"
        + "cu un procent ridicat de populație romă, prin finanțarea proiectelor de nivel\r\n"
        + "comunitar (dezvoltate în cadrul unei singure UAT) care abordează necesități\r\n"
        + "prioritare specifice identificate într-un mod participativ.";

        assertEquals(expectedResult, purposeProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getResourceAsStream("singleProjectTestDataWithoutPurpose.txt"));
        assertEquals("", purposeProcessor.getPropertyValue(projectTestData));
    }
}
