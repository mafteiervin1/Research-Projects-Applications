package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FinancesProcessorTest {

    private Processor financesProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        financesProcessor = new FinancesProcessor();
    }

    @Test
    void testGetPropertyValueReturnFinances() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties.txt")
        );
        String expectedResult = "Mecanismul Financiar SEE\n" +
                "2014-2021 si guvernul Romaniei\n" +
                "Finantare primita: max. 100%";
        assertEquals(expectedResult, financesProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getResourceAsStream("singleProjectTestDataWithoutFinances.txt"));
        assertEquals("", financesProcessor.getPropertyValue(projectTestData));
    }
}
