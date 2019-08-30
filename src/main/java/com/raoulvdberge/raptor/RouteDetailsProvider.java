package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorStop;

import java.util.List;

public interface RouteDetailsProvider {
    boolean isStopBefore(RaptorRoute route, RaptorStop stopA, RaptorStop stopB);

    int getRouteStopIndex(RaptorRoute route, RaptorStop stop);

    List<RaptorStop> getRoutePath(RaptorRoute route);
}
