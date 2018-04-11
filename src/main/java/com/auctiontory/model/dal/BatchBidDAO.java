package com.auctiontory.model.dal;

import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.dal.exception.BiddingException;
import com.auctiontory.model.entity.UserBatchBid;

public interface BatchBidDAO extends IDaoBase<UserBatchBid> {
    boolean alreadyBid(int userId, int batchAuctionId);

    boolean bid(int userId, int batchAuctionId, int bidAmount) throws AlreadyHighestBidderException,AuctionAlreadyClosedException;
}
