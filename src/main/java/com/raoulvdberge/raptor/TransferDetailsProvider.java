package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Stop;
import com.raoulvdberge.raptor.model.TransferLeg;

import java.util.List;

public interface TransferDetailsProvider {
    List<TransferLeg> getTransfersForStop(Stop stop);
}
