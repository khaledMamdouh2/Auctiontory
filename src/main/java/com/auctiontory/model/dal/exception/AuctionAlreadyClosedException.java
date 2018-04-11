package com.auctiontory.model.dal.exception;

public class AuctionAlreadyClosedException extends BiddingException{
    public AuctionAlreadyClosedException() {
        super("Cannot bid; Auction is already closed");
    }
}
