package com.webencyclop.core.mftool.models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Meta {

    @JsonProperty("fund_house")
    private String fundHouse;
    @JsonProperty("scheme_type")
    private String schemeType;
    @JsonProperty("scheme_category")
    private String schemeCategory;
    @JsonProperty("scheme_code")
    private String schemeCode;
    @JsonProperty("scheme_name")
    private String schemeName;

    public String getFundHouse() {
        return fundHouse;
    }

    public void setFundHouse(String fundHouse) {
        this.fundHouse = fundHouse;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getSchemeCategory() {
        return schemeCategory;
    }

    public void setSchemeCategory(String schemeCategory) {
        this.schemeCategory = schemeCategory;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var meta = (Meta) o;
        return Objects.equals(schemeCode, meta.schemeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeCode);
    }

    @Override
    public String toString() {
        return "Meta{" +
                "fundHouse='" + fundHouse + '\'' +
                ", schemeType='" + schemeType + '\'' +
                ", schemeCategory='" + schemeCategory + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", schemeName='" + schemeName + '\'' +
                '}';
    }
}
