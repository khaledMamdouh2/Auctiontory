package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchBidController;
import com.auctiontory.controller.listener.BatchAuctionInterceptor;
import com.auctiontory.model.dal.BatchBidDAO;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.UserBatchBid;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.transaction.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BatchBidControllerImpl implements BatchBidController {
    @Inject
    private BatchBidDAO batchBidDao;

    @Resource
    private UserTransaction utx;

    @Inject
    BatchAuctionInterceptor batchAuctionInterceptor;

    @Override
    public boolean alreadyBid(int userId, int batchAuctionId) {
        return batchBidDao.alreadyBid(userId, batchAuctionId);
    }

    @Interceptors(BatchAuctionInterceptor.class)
    @Override
    public boolean bid(int userId, int batchAuctionId, int bidAmount) throws AlreadyHighestBidderException, AuctionAlreadyClosedException {
        boolean bid = false;
        try {
            utx.begin();
            bid = batchBidDao.bid(userId, batchAuctionId, bidAmount);
            utx.commit();
        } catch (AlreadyHighestBidderException | AuctionAlreadyClosedException e) {
            try {
                utx.commit();
            } catch (RollbackException e1) {
                e1.printStackTrace();
            } catch (HeuristicMixedException e1) {
                e1.printStackTrace();
            } catch (HeuristicRollbackException e1) {
                e1.printStackTrace();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
            throw e;
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }


        return bid;
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
