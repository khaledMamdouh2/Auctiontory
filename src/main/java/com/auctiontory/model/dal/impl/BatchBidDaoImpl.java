package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.dal.BatchBidDAO;
import com.auctiontory.model.dal.UserDAO;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.dal.exception.BiddingException;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserBatchBid;
import com.auctiontory.model.entity.UserBatchBidPK;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named("batchBidDao")
public class BatchBidDaoImpl implements BatchBidDAO, Serializable {
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    @Inject
    private BatchAuctionDAO batchDao;

    @Inject
    UserDAO userDao;

    @Override
    public boolean alreadyBid(int userId, int batchAuctionId) {
        boolean alreadyBid = false;
        List<UserBatchBid> userBatchBids = em.createNamedQuery("UserBatchBid.findByBatchIdAndUserId")
                .setParameter("batchId", batchAuctionId)
                .setParameter("userId", userId)
                .getResultList();
        if (userBatchBids != null && userBatchBids.size() > 0) {
            UserBatchBid userBatchBid = userBatchBids.get(0);
            if (userBatchBid != null) {
                alreadyBid = true;
            } else {
                alreadyBid = false;
            }
        } else {
            alreadyBid = false;
        }
        return alreadyBid;
    }

    @Override
    public boolean bid(int userId, int batchAuctionId, int bidAmount) throws AlreadyHighestBidderException, AuctionAlreadyClosedException {
        boolean bid = false;
        BatchAuction batchAuction = batchDao.get(batchAuctionId);
        User user = userDao.get(userId);
        if (batchAuction.getHighestBidderId() != null) {
            if (user.getUserName().equals(batchAuction.getHighestBidderId().getUserName())) {
                throw new AlreadyHighestBidderException();
            }
        }
        if (batchAuction != null && user != null) {
            if (bidAmount >= batchAuction.getMinBid()) {
                if (bidAmount > batchAuction.getHighestBid()) {
                    if (alreadyBid(userId, batchAuctionId)) {
                        UserBatchBidPK userBatchBidPK = new UserBatchBidPK(userId, batchAuctionId);
                        UserBatchBid userBatchBid = get(userBatchBidPK);
                        userBatchBid.setPrice(bidAmount);
                        em.persist(userBatchBid);
                        bid = true;
                    } else {
                        UserBatchBidPK userBatchBidPK = new UserBatchBidPK(userId, batchAuctionId);
                        UserBatchBid userBatchBid = new UserBatchBid(userBatchBidPK);
                        userBatchBid.setPrice(bidAmount);
                        em.persist(userBatchBid);
                        bid = true;
                    }
                }
            }
        }
        return bid;
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
        return em.find(UserBatchBid.class, id);
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
