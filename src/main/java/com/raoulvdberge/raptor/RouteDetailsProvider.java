package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Stop;

import java.util.List;

public interface RouteDetailsProvider {
    boolean isStopBefore(Route route, Stop stopA, Stop stopB);

    int getRouteStopIndex(Route route, Stop stop);

    List<Stop> getRoutePath(Route route);
}
