package com.webencyclop.core.mftool;

import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.output.SchemeDetails;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
}