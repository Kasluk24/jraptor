package com.raoulvdberge.raptor.builder;

import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.model.impl.StopImpl;
import com.raoulvdberge.raptor.model.impl.TripImpl;

import java.util.List;
import java.util.Map;

/**
 * The result returned by the {@link RaptorBuilder}.
 */
public class RaptorBuilderResult {
    private final List<TripImpl> trips;
    private final Map<StopImpl, List<TransferLeg<StopImpl>>> transfers;

    public RaptorBuilderResult(List<TripImpl> trips, Map<StopImpl, List<TransferLeg<StopImpl>>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    /**
     * @return the trips
     */
    public List<TripImpl> getTrips() {
        return trips;
    }

    /**
     * @return the transfers
     */
    public Map<StopImpl, List<TransferLeg<StopImpl>>> getTransfers() {
        return transfers;
    }
}
