package com.webencyclop.core.mftool;

import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.output.SchemeDetails;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

class MFToolTest {

    @Test
    void matchingSchemeName() throws IOException {
        MFTool tool = new MFTool();
        List<SchemeNameCodePair> list = tool.matchingScheme("Axis");
        Assertions.assertNotNull(list);
    }

    @Test
    void testAllSchemes() throws IOException {
        MFTool tool = new MFTool();
        List<SchemeNameCodePair> list = tool.allSchemes();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 40_000);
    }

    @Test
    void testSchemeDetails() throws IOException {
        MFTool tool = new MFTool();
        SchemeDetails details = tool.schemeDetails("120503");
        Assertions.assertEquals("Axis Mutual Fund", details.getFundHouse());
    }

    @Test
    void testSchemeNav() throws IOException {
        MFTool tool = new MFTool();
        List<Data> list = tool.historicNavForScheme("120503");
        System.out.println(list.size());
        Assertions.assertTrue(list.size() > 2098); // 2098 days old minimum
    }

    @Test
    void testGetCurrentNav() throws IOException {
        MFTool tool = new MFTool();
        BigDecimal nav = tool.getCurrentNav("120503");
        Assertions.assertNotNull(nav);
    }

    @Test
    void testGetNavFor() throws IOException {
        MFTool tool = new MFTool();
        BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
        Assertions.assertNotNull(nav);
    }
    @Test
    void testGetNavForHoliday() throws IOException {
        MFTool tool = new MFTool();
        BigDecimal nav = tool.getNavFor("120711", LocalDate.parse("2021-08-21"));
        Assertions.assertNotNull(nav);
    }
}