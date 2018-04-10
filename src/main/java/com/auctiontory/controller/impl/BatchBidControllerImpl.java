package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchBidController;
import com.auctiontory.model.entity.UserBatchBid;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BatchBidControllerImpl implements BatchBidController {
    @Override
    public boolean alreadyBid(int userId, int batchAuctionId) {
        return false;
    }

    @Override
    public boolean bid(int userId, int batchAuctionId, int bidAmount) {
        return false;
    }

    @Override
    public List<UserBatchBid> loadAll() {
        return null;
    }

    @Override
    public void save(UserBatchBid domain) {

    }

    @Override
    public void update(UserBatchBid domain) {

    }

    @Override
    public void delete(UserBatchBid domain) {

    }

    @Override
    public UserBatchBid get(Serializable id) {
        return null;
    }
}
