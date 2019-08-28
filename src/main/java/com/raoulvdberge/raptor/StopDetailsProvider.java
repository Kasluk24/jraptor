package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Stop;

import java.util.Set;

public interface StopDetailsProvider {
    Set<Stop> getStops();

    Set<Route> getRoutesByStop(Stop stop);
}
