package com.webencyclop.core.mftool.models.input;

import java.util.List;
import java.util.Objects;

public class SchemeDetails {

    private Meta meta;
    private List<Data> data;

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
        SchemeDetails that = (SchemeDetails) o;
        return Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta);
    }

    @Override
    public String toString() {
        return "SchemeDetails{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
