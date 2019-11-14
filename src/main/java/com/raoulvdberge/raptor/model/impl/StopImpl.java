package com.raoulvdberge.raptor.model.impl;

import java.util.Objects;

public class StopImpl {
    private final String name;

    public StopImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopImpl stop = (StopImpl) o;
        return name.equals(stop.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
