package com.raoulvdberge.raptor.provider;

import java.util.List;

public interface RouteDetailsProvider<R, S> {
    boolean isStopBefore(R route, S stopA, S stopB);

    int getRouteStopIndex(R route, S stop);

    List<S> getRoutePath(R route);
}
