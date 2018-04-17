package com.auctiontory.controller;

import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserPartBid;

import java.util.List;

public interface PartsBidController extends IControllerBase<UserPartBid> {

    boolean alreadyBid(int userId, int partProductId, int partAuctionId);

    boolean bid(int userId ,int partProductId, int partAuctionId, int bidAmmount)throws AlreadyHighestBidderException,AuctionAlreadyClosedException;

    List<User> getOtherBidders(int userId,int partProductId, int partAuctionId);

}
