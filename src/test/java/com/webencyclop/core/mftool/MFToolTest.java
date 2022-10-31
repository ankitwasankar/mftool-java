package com.webencyclop.core.mftool;

import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.output.SchemeDetails;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;


class MFToolTest {

    private static MFTool tool;

    @BeforeAll
    public static void init() {
        tool = Mockito.spy(new MFTool());
    }

    @Test
    @DisplayName("Test Matching Schemes with keyword Axis")
    void testMatchingSchemeName() throws IOException {
        List<SchemeNameCodePair> list = tool.matchingScheme("Axis");
        Assertions.assertNotNull(list);
    }

    @Test
    @DisplayName("Test Matching Schemes with keyword Axis with cached data")
    void testMatchingSchemeNameWithoutUpdateMock() throws IOException {
        when(tool.isSchemePairNeedToUpdate()).thenReturn(false);
        List<SchemeNameCodePair> list = tool.matchingScheme("Axis");
        Assertions.assertNotNull(list);
        list = tool.matchingScheme("Axis");
        Assertions.assertNotNull(list);
    }

    @Test
    @DisplayName("Test List of all schemes")
    void testAllSchemes() throws IOException {
        when(tool.isSchemePairNeedToUpdate()).thenReturn(true);
        List<SchemeNameCodePair> list = tool.allSchemes();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 40_000);
    }

    @Test
    @DisplayName("Test List of all schemes with cached data")
    void testAllSchemesWithoutMock() throws IOException {
        when(tool.isSchemePairNeedToUpdate()).thenReturn(false);
        List<SchemeNameCodePair> list = tool.allSchemes();
        Assertions.assertNotNull(list);
        list = tool.allSchemes();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 40_000);
    }

    @Test
    @DisplayName("Test Scheme Details for Axis Mutual Fund: 120503")
    void testSchemeDetails() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        SchemeDetails details = tool.schemeDetails("120503");
        Assertions.assertEquals("Axis Mutual Fund", details.getFundHouse());
    }

    @Test
    @DisplayName("Test Scheme Details for Axis Mutual Fund: 120503 without cached")
    void testSchemeDetailsWithMock() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(false);
        SchemeDetails details = tool.schemeDetails("120503");
        Assertions.assertEquals("Axis Mutual Fund", details.getFundHouse());
    }

    @Test
    @DisplayName("Test Scheme Details for Axis Mutual Fund: 120503 with cached data")
    void testSchemeDetailsWithoutMock() throws IOException {
        SchemeDetails details = tool.schemeDetails("120503");
        Assertions.assertEquals("Axis Mutual Fund", details.getFundHouse());
        details = tool.schemeDetails("120503");
        Assertions.assertEquals("Axis Mutual Fund", details.getFundHouse());
    }

    @Test
    @DisplayName("Test Nav for Axis Mutual Fund: 120503")
    void testSchemeNav() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        List<Data> list = tool.historicNavForScheme("120503");
        Assertions.assertTrue(list.size() > 2098); // 2098 days old minimum
    }

    @Test
    @DisplayName("Test Nav for Axis Mutual Fund: 120503 with cached data")
    void testSchemeNavWithoutMock() throws IOException {
        List<Data> list = tool.historicNavForScheme("120503");
        Assertions.assertTrue(list.size() > 2098); // 2098 days old minimum
        list = tool.historicNavForScheme("120503");
        Assertions.assertTrue(list.size() > 2098); // 2098 days old minimum
    }

    @Test
    @DisplayName("Test Nav for Axis Mutual Fund: 120503 without cached data")
    void testSchemeNavWithMock() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(false);
        List<Data> list = tool.historicNavForScheme("120503");
        Assertions.assertTrue(list.size() > 2098); // 2098 days old minimum
    }

    @Test
    @DisplayName("Test current NAV for Axis Mutual Fund: 120503")
    void testGetCurrentNav() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        BigDecimal nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test current NAV for Axis Mutual Fund: 120503 without cached data")
    void testGetCurrentNavWithMock() throws IOException {
        BigDecimal nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(false);
        nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test current NAV for Axis Mutual Fund: 120503 with cached data")
    void testGetCurrentNavWithoutMock() throws IOException {
        BigDecimal nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
        nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test NAV at date 2021/07/13 for Axis Mutual Fund: 120503")
    void testGetNavFor() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test NAV at date 2021/07/13 for Axis Mutual Fund: 120503 without cached data")
    void testGetNavForWithMock() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(false);
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test NAV at date 2021/07/13 for Axis Mutual Fund: 120503 with cached data")
    void testGetNavForWithoutMock() throws IOException {
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
        Assertions.assertNotNull(nav);
        nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test NAV at date 2099/07/13 for Axis Mutual Fund: 120503")
    void testGetNavForFutureDate() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2099-07-13"));
        Assertions.assertEquals(BigDecimal.ZERO, nav);
    }

    @Test
    @DisplayName("Test NAV at date 2099/07/13 for Axis Mutual Fund: 120503 with cached data")
    void testGetNavForFutureDateWithoutMock() throws IOException {
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2099-07-13"));
        Assertions.assertEquals(BigDecimal.ZERO, nav);
        nav = tool.getNavFor("120503", LocalDate.parse("2099-07-13"));
        Assertions.assertEquals(BigDecimal.ZERO, nav);
    }

    @Test
    @DisplayName("Test NAV at holiday for 120711")
    void testGetNavForHoliday() throws IOException {
        when(tool.isSchemeDetailNeedToUpdate("120503")).thenReturn(true);
        BigDecimal nav = tool.getNavFor("120711", LocalDate.parse("2021-08-21"));
        Assertions.assertNotNull(nav);
    }

    @Test
    @DisplayName("Test NAV at holiday for 120711 with cached data")
    void testGetNavForHolidayWithoutMock() throws IOException {
        BigDecimal nav = tool.getNavFor("120711", LocalDate.parse("2021-08-21"));
        Assertions.assertNotNull(nav);
        nav = tool.getNavFor("120711", LocalDate.parse("2021-08-21"));
        Assertions.assertNotNull(nav);
    }

}