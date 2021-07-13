package com.webencyclop.core.mftool.models.output;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SchemeNameCodePair implements Comparable<SchemeNameCodePair> {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemeNameCodePair that = (SchemeNameCodePair) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull SchemeNameCodePair o) {
        return name.compareTo(o.getName());
    }
}
