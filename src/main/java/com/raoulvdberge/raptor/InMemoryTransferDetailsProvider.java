package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorStop;
import com.raoulvdberge.raptor.model.RaptorTransferLeg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryTransferDetailsProvider implements TransferDetailsProvider {
    private final Map<RaptorStop, List<RaptorTransferLeg>> transfers;

    public InMemoryTransferDetailsProvider(Map<RaptorStop, List<RaptorTransferLeg>> transfers) {
        this.transfers = transfers;
    }

    @Override
    public List<RaptorTransferLeg> getTransfersForStop(RaptorStop stop) {
        return this.transfers.getOrDefault(stop, new ArrayList<>());
    }
}
