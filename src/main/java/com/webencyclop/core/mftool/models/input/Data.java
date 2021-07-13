package com.webencyclop.core.mftool.models.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Data {

    private LocalDate date;
    private BigDecimal nav;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
