package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EligibleActivitiesProcessorTest {

    private Processor eligibleActivitiesProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        eligibleActivitiesProcessor = new EligibleActivitiesProcessor();
    }

    @Test
    void testGetPropertyValueReturnEligibleActivitiesSimple() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties.txt")
        );
        String expectedResult = "1. Educație (îmbunătățirea condițiilor/ infrastructurii educaționale)\n" +
                "2. Sănătate (îmbunătățirea condițiilor/ infrastructurii de sănătate)\n" +
                "3. Locuire (îmbunătățirea condițiilor de locuit)\n" +
                "4. Juridic (limitat la obținerea documentelor de identitate sau proprietate și la\n" +
                "clarificarea situațiilor individuale de locuire).";
        assertEquals(expectedResult, eligibleActivitiesProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEligibleActivitiesComplex() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("singleProjectTestDataWithAllProperties2.txt")
        );
        String expectedResult = "\uF0B7 Sprijin pentru accesul și/sau menținerea pe piața muncii\n" +
                "\uF0B7 Susținerea antreprenoriatului în cadrul comunității, inclusiv a ocupării pe cont\n" +
                "propriu\n" +
                "\uF0B7 Sprijinirea dezvoltării/ furnizării de servicii sociale/ medicale/ medico-sociale,\n" +
                "inclusiv în cadrul centrelor comunitare integrate\n" +
                "\uF0B7 Sprijin pentru creșterea accesului și participării la educație\n" +
                "\uF0B7 Activități de îmbunătățire a condițiilor de locuit ale persoanelor din grupul țintă\n" +
                "\uF0B7 Asistență juridică pentru reglementarea actelor de identitate, de proprietate, de\n" +
                "stare civilă\n" +
                "\uF0B7 Combaterea discriminării și a segregării";
        assertEquals(expectedResult, eligibleActivitiesProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
                this.getClass()
                        .getResourceAsStream("singleProjectTestDataWithoutEligibleActivities.txt"));
        assertEquals("", eligibleActivitiesProcessor.getPropertyValue(projectTestData));
    }
}
