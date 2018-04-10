package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchBidController;
import com.auctiontory.model.dal.BatchBidDAO;
import com.auctiontory.model.entity.UserBatchBid;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BatchBidControllerImpl implements BatchBidController {
    @Inject
    private BatchBidDAO batchBidDao;

    @Override
    public boolean alreadyBid(int userId, int batchAuctionId) {
        return batchBidDao.alreadyBid(userId, batchAuctionId);
    }

    @Override
    public boolean bid(int userId, int batchAuctionId, int bidAmount) {
        return batchBidDao.bid(userId, batchAuctionId, bidAmount);
    }

    @Override
    public List<UserBatchBid> loadAll() {
        return batchBidDao.loadAll();
    }

    @Override
    public void save(UserBatchBid domain) {
        batchBidDao.save(domain);
    }

    @Override
    public void update(UserBatchBid domain) {
        batchBidDao.update(domain);
    }

    @Override
    public void delete(UserBatchBid domain) {
        batchBidDao.delete(domain);
    }

    @Override
    public UserBatchBid get(Serializable id) {
        return batchBidDao.get(id);
    }
}
