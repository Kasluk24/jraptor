package com.raoulvdberge.raptor.provider;

import java.util.Set;

public interface StopDetailsProvider<S, R> {
    Set<S> getStops();

    Set<R> getRoutesByStop(S stop);
}
