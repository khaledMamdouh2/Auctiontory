package com.auctiontory.model.dal;

import com.auctiontory.model.entity.UserBatchBid;

public interface BatchBidDAO extends IDaoBase<UserBatchBid> {
    boolean alreadyBid(int userId, int batchAuctionId);

    boolean bid(int userId, int batchAuctionId, int bidAmount);
}
