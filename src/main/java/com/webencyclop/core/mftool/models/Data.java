package com.webencyclop.core.mftool.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Data {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private BigDecimal nav;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var data = (Data) o;
        return Objects.equals(date, data.date) &&
                Objects.equals(nav, data.nav);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, nav);
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                ", nav=" + nav +
                '}';
    }
}
