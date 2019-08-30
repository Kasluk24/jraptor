package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorStop;
import com.raoulvdberge.raptor.model.RaptorTransferLeg;

import java.util.List;

public interface TransferDetailsProvider {
    List<RaptorTransferLeg> getTransfersForStop(RaptorStop stop);
}
