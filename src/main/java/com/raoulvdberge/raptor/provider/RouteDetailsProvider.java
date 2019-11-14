package com.raoulvdberge.raptor.provider;

import java.util.List;

/**
 * Provider class for the Raptor algorithm used to provide information about routes.
 *
 * @param <R> the route
 * @param <S> the stop
 */
public interface RouteDetailsProvider<R, S> {
    /**
     * @param route the route
     * @param stopA stop A
     * @param stopB stop B
     * @return true if stop A is before stop B in the given route, false otherwise
     */
    boolean isStopBefore(R route, S stopA, S stopB);

    /**
     * Returns the index of the given stop in a route.
     *
     * @param route the route
     * @param stop  the stop
     * @return the index
     */
    int getRouteStopIndex(R route, S stop);

    /**
     * Returns a list of stops for the given route. This list of stops is the route that this route follows.
     *
     * @param route the route
     * @return the route path
     */
    List<S> getRoutePath(R route);
}
