package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.TransferLeg;

import java.util.List;

public interface TransferDetailsProvider<S> {
    List<TransferLeg<S>> getTransfersForStop(S stop);
}
