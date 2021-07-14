package com.webencyclop.core.mftool;

import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class MFToolTest {

    @Test
    void matchingSchemeName() throws IOException {
        MFTool tool = new MFTool();
        List<SchemeNameCodePair> list = tool.matchingSchemeName("Axis");
        Assertions.assertNotNull(list);
    }

    @Test
    void testAllSchemes() throws IOException {
        MFTool tool = new MFTool();
        List<SchemeNameCodePair> list = tool.allSchemes();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 40_000);
    }
}