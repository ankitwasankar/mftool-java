package com.webencyclop.core.mftool.models.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputSchemeDetailsTest {

    @Test
    void testEquals() {
        InputSchemeDetails objOne = createObject("Axis");
        InputSchemeDetails objTwo = createObject("Axis");
        InputSchemeDetails objThree = createObject("DSP");
        Assertions.assertNotEquals(objOne, objThree);
        Assertions.assertEquals(objOne, objTwo);
    }

    private InputSchemeDetails createObject(String details) {
        InputSchemeDetails object = new InputSchemeDetails();
        Meta meta = new Meta();
        meta.setFundHouse(details);
        meta.setSchemeCategory(details);
        meta.setSchemeCode(details);
        meta.setSchemeType(details);
        meta.setSchemeName(details);
        object.setMeta(meta);
        return object;
    }
}