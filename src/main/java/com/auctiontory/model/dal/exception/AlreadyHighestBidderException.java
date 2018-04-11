package com.auctiontory.model.dal.exception;

public class AlreadyHighestBidderException extends BiddingException {
    public AlreadyHighestBidderException() {
        super("Already highest bidder!");
    }
}
