package com.auctiontory.controller.impl;

import com.auctiontory.controller.PartsBidController;
import com.auctiontory.controller.listener.BatchAuctionInterceptor;
import com.auctiontory.controller.listener.PartAuctionInterceptor;
import com.auctiontory.model.dal.BatchBidDAO;
import com.auctiontory.model.dal.PartsBidDAO;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserPartBid;

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
public class PartsBidControllerImpl implements PartsBidController {

    @Inject
    private PartsBidDAO partsBidDao;


    @Inject
    private PartAuctionInterceptor partAuctionInterceptor;


    @Override
    public boolean alreadyBid(int userId, int partProductId, int partAuctionId) {
//        boolean alreadyBid = false;
//        try {
//            utx.begin();
        return partsBidDao.alreadyBid(userId, partProductId, partAuctionId);
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
//
//        return alreadyBid;
    }

    @Override
    public boolean bid(int userId, int partProductId, int partAuctionId, int bidAmount) throws AlreadyHighestBidderException, AuctionAlreadyClosedException {
//        boolean bid = false;
//        try {
//            utx.begin();
        return partsBidDao.bid(userId, partProductId, partAuctionId, bidAmount);
//            utx.commit();
//        } catch (AlreadyHighestBidderException | AuctionAlreadyClosedException e) {
//            try {
//                utx.commit();
//            } catch (RollbackException e1) {
//                e1.printStackTrace();
//            } catch (HeuristicMixedException e1) {
//                e1.printStackTrace();
//            } catch (HeuristicRollbackException e1) {
//                e1.printStackTrace();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//            throw e;
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
//
//
//        return bid;
    }


    @Override
    public List<UserPartBid> loadAll() {
//        List<UserPartBid> userPartBids = null;
//        try {
//            utx.begin();
        return partsBidDao.loadAll();
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return userPartBids;
    }

    @Override
    public void save(UserPartBid domain) {
//        try {
//            utx.begin();
        partsBidDao.save(domain);
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
    }

    @Override
    public void update(UserPartBid domain) {
//        try {
//            utx.begin();
        partsBidDao.update(domain);
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
    }

    @Override
    public void delete(UserPartBid domain) {
//        try {
//            utx.begin();
        partsBidDao.delete(domain);
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
    }

    @Override
    public UserPartBid get(Serializable id) {
//        UserPartBid userPartBid = null;
//        try {
//            utx.begin();
        return partsBidDao.get(id);
//            utx.commit();
//        } catch (Exception e) {
//            try {
//                utx.rollback();
//            } catch (SystemException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return userPartBid;
    }
}
