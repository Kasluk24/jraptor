package com.raoulvdberge.raptor.model;

import java.util.Objects;

public class RaptorStop {
    private final String name;

    public RaptorStop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaptorStop stop = (RaptorStop) o;
        return name.equals(stop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
