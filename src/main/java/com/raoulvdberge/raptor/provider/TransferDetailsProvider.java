package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.TransferLeg;

import java.util.List;

/**
 * Provider class for the Raptor algorithm used to provide information about transfers.
 *
 * @param <S> the stop
 */
public interface TransferDetailsProvider<S> {
    /**
     * Returns transfers for a given stop.
     *
     * @param stop the st op
     * @return the transfers
     */
    List<TransferLeg<S>> getTransfersForStop(S stop);
}
