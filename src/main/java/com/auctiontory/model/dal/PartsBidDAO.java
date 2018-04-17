package com.auctiontory.model.dal;

import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserPartBid;

import java.util.List;

public interface PartsBidDAO extends IDaoBase<UserPartBid> {
    boolean alreadyBid(int userId, int partProductId, int partAuctionId);

    boolean bid(int userId ,int partProductId, int partAuctionId, int bidAmount)throws AlreadyHighestBidderException,AuctionAlreadyClosedException;

}
