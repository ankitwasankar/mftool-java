package com.webencyclop.core.mftool.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class DataTest {

    @Test
    void testEquals() {
        LocalDate date = LocalDate.parse("2021-07-13");
        LocalDate nextDate = LocalDate.parse("2021-07-14");
        Data dataOne = createDataObject(date, BigDecimal.ONE);
        Data dataTwo = createDataObject(date, BigDecimal.ONE);
        Data dataThree = createDataObject(nextDate, BigDecimal.ONE);
        Data dataFour = createDataObject(date, BigDecimal.ZERO);
        Assertions.assertEquals(dataOne, dataTwo);
        Assertions.assertNotEquals(dataOne, dataThree);
        Assertions.assertNotEquals(dataOne, dataFour);
    }

    @Test
    void testCompare() {
        LocalDate date = LocalDate.parse("2021-07-13");
        LocalDate nextDate = LocalDate.parse("2021-07-14");
        Data dataOne = createDataObject(date, BigDecimal.ONE);
        Data dataTwo = createDataObject(nextDate, BigDecimal.ONE);
        Assertions.assertNotEquals(0, dataOne.compareTo(dataTwo));
    }

    private Data createDataObject(LocalDate date, BigDecimal nav) {
        Data data = new Data();
        data.setDate(date);
        data.setNav(nav);
        Assertions.assertEquals(date, data.getDate());
        Assertions.assertEquals(nav, data.getNav());
        return data;
    }

}