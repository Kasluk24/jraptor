package com.raoulvdberge.raptor.provider.impl;

import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.provider.TransferDetailsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InMemoryTransferDetailsProvider<S> implements TransferDetailsProvider<S> {
    private final Map<S, List<TransferLeg<S>>> transfers;

    /**
     * Transfers is a map where the key is the stop, and the value a list of transfers at this stop where the stop is the origin of those transfers.
     *
     * @param transfers the transfers
     */
    public InMemoryTransferDetailsProvider(Map<S, List<TransferLeg<S>>> transfers) {
        this.transfers = transfers;
    }

    @Override
    public List<TransferLeg<S>> getTransfersForStop(S stop) {
        return transfers.getOrDefault(stop, Collections.emptyList());
    }
}
