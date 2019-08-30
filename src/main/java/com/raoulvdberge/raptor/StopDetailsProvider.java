package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorStop;

import java.util.Set;

public interface StopDetailsProvider {
    Set<RaptorStop> getStops();

    Set<RaptorRoute> getRoutesByStop(RaptorStop stop);
}
