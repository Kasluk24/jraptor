package com.raoulvdberge.raptor.builder;

/**
 * Throwed when trying to build an invalid trip or transfer in {@link RaptorBuilder}.
 */
public class RaptorBuilderException extends RuntimeException {
    public RaptorBuilderException(String message) {
        super(message);
    }
}
