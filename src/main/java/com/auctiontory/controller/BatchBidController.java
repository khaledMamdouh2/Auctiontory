package com.auctiontory.controller;

import com.auctiontory.model.entity.UserBatchBid;

public interface BatchBidController extends IControllerBase<UserBatchBid> {

    boolean alreadyBid(int userId, int batchAuctionId);

    boolean bid(int userId, int batchAuctionId, int bidAmount);
}
