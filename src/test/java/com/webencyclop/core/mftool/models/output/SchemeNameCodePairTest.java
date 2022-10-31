package com.webencyclop.core.mftool.models.output;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchemeNameCodePairTest {

    @Test
    void testEquals() {
        SchemeNameCodePair pairOne = createObject("12345", "TEST");
        SchemeNameCodePair pairTwo = createObject("12345", "TEST");
        SchemeNameCodePair pairThree = createObject("12346", "TEST1");
        Assertions.assertEquals(pairOne, pairTwo);
        Assertions.assertNotEquals(pairOne, pairThree);
    }

    private SchemeNameCodePair createObject(String code, String name) {
        SchemeNameCodePair object = new SchemeNameCodePair();
        object.setCode(code);
        object.setName(name);
        Assertions.assertEquals(code, object.getCode());
        Assertions.assertEquals(name, object.getName());
        return object;
    }

}