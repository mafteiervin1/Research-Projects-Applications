package com.seman.projhandle.processor;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoreInfoProcessorTest {

    private Processor moreInfoProcessor;

    private String projectTestData;

    @BeforeAll
    void beforeAll() {
        moreInfoProcessor = new MoreInfoProcessor();
    }

    @Test
    void testGetPropertyValueReturnEligibleApplicants() {
        projectTestData = IOUtils.toString(
            this.getClass()
                .getClassLoader()
                .getResourceAsStream("singleProjectTestDataWithAllProperties.txt"));
        String expectedResult = "La nivel de proiect, valoare minimă aferentă cheltuielilor eligibile este de 30 000 /\n" +
            "20.000 euro\n" +
            "La nivel de strategie, valoarea totală maximă a pachetului de proiecte de\n" +
            "infrastructură (aferent fiecărei strategii de dezvoltare locală) va fi de:\n" +
            "a. 4.900.000 euro in cazul SDL-urilor, care nu au proiecte dedicate întreprinderilor\n" +
            "de economie socială de inserție\n" +
            "b. 4.300.000 euro în cazul SDL-urilor care au proiecte dedicate intreprinderilor de\n" +
            "economie socială de inserție\n" +
            "Pentru apelul de proiecte POR/2019/9/9.1/1/7REGIUNI, cererile de finantare vor\n" +
            "putea fi transmise pana la data de 31 decembrie 2021, ora 10.00\n" +
            "http://inforegionordest.ro/prioritatea-9.1";
        assertEquals(expectedResult, moreInfoProcessor.getPropertyValue(projectTestData));
    }

    @Test
    void testGetPropertyValueReturnEmptyIfNoKeywordsFound() {
        projectTestData = IOUtils.toString(
            this.getClass()
                .getResourceAsStream("singleProjectTestDataWithoutMoreInfo.txt"));
        assertEquals("", moreInfoProcessor.getPropertyValue(projectTestData));
    }
}
