package com.webencyclop.core.mftool.models.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.output.SchemeDetails;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
public class InputSchemeDetails {

    private Meta meta;
    private List<Data> data;
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputSchemeDetails that = (InputSchemeDetails) o;
        return Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta);
    }

    @Override
    public String toString() {
        return "InputSchemeDetails{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    public SchemeDetails mapToSchemeDetail() {
        var details = new SchemeDetails();
        details.setFundHouse(this.meta.getFundHouse());
        details.setSchemeCategory(this.meta.getSchemeCategory());
        details.setSchemeCode(this.meta.getSchemeCode());
        details.setSchemeType(this.meta.getSchemeType());
        details.setSchemeName(this.meta.getSchemeName());
        return details;
    }

    public List<Data> mapToNav() {
        return this.getData();
    }

}
