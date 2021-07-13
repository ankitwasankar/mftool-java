package com.webencyclop.core.mftool.models.input;

import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;

public class Scheme {

    private String schemeCode;
    private String schemeName;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public SchemeNameCodePair map(Scheme scheme) {
        var pair = new SchemeNameCodePair();
        pair.setCode(scheme.getSchemeCode());
        pair.setName(scheme.getSchemeName());
        return pair;
    }
}
