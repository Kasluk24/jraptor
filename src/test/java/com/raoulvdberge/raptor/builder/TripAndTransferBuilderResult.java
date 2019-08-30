package com.raoulvdberge.raptor.builder;

import com.raoulvdberge.raptor.model.Stop;
import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.model.TripImpl;

import java.util.List;
import java.util.Map;

public class TripAndTransferBuilderResult {
    private final List<TripImpl> trips;
    private final Map<Stop, List<TransferLeg<Stop>>> transfers;

    public TripAndTransferBuilderResult(List<TripImpl> trips, Map<Stop, List<TransferLeg<Stop>>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    public List<TripImpl> getTrips() {
        return trips;
    }

    public Map<Stop, List<TransferLeg<Stop>>> getTransfers() {
        return transfers;
    }
}
