package com.auctiontory.controller;

import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserBatchBid;

import java.util.List;

public interface BatchBidController extends IControllerBase<UserBatchBid> {

    boolean alreadyBid(int userId, int batchAuctionId);

    boolean bid(int userId, int batchAuctionId, int bidAmount) throws AlreadyHighestBidderException,AuctionAlreadyClosedException;

    List<User> getOtherBidders(int userId , int batchAuctionId);
}
