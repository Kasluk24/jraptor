package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Stop;
import com.raoulvdberge.raptor.model.TransferLeg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryTransferDetailsProvider implements TransferDetailsProvider<Stop> {
    private final Map<Stop, List<TransferLeg<Stop>>> transfers;

    public InMemoryTransferDetailsProvider(Map<Stop, List<TransferLeg<Stop>>> transfers) {
        this.transfers = transfers;
    }

    @Override
    public List<TransferLeg<Stop>> getTransfersForStop(Stop stop) {
        return this.transfers.getOrDefault(stop, new ArrayList<>());
    }
}
