package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.PartsAuctionDAO;
import com.auctiontory.model.dal.PartsBidDAO;
import com.auctiontory.model.dal.UserDAO;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named("partsBidDao")
public class PartsBidDaoImpl implements PartsBidDAO {
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    @Inject
    private PartsAuctionDAO partsDao;

    @Inject
    UserDAO userDao;

    @Override
    public boolean alreadyBid(int userId, int partProductId, int partAuctionId) {
        boolean alreadyBid = false;
        List<UserPartBid> userPartBids = em.createNamedQuery("UserPartBid.findByUserIdAndPartProductId")
                .setParameter("partProductId", partProductId)
                .setParameter("userId", userId)
                .getResultList();
        if (userPartBids != null && userPartBids.size() > 0) {
            UserPartBid userPartBid = userPartBids.get(0);
            if (userPartBid != null) {
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
    public boolean bid(int userId, int partProductId, int partAuctionId, int bidAmount) throws AlreadyHighestBidderException, AuctionAlreadyClosedException {
        boolean bid = false;
        boolean isActive = partsDao.isActive(partAuctionId);
        if (isActive) {
            PartsAuction partsAuction = partsDao.get(partAuctionId);
            User user = userDao.get(userId);
            PartsProduct partsProduct = null;
            for (PartsProduct product : partsAuction.getPartsProductList()) {
                if (product.getId().equals(partProductId)) {
                    partsProduct = product;
                    break;
                }
            }
            if (partsProduct.getHighestBidderId() != null) {
                if (user.getUserName().equals(partsProduct.getHighestBidderId().getUserName())) {
                    throw new AlreadyHighestBidderException();
                }
            }
            if (partsProduct != null && user != null) {
                if (bidAmount >= partsProduct.getMinBid()) {
                    if (bidAmount > partsProduct.getHighestBid()) {
                        if (alreadyBid(userId, partProductId, partAuctionId)) {
                            UserPartBidPK userPartBidPK = new UserPartBidPK(userId, partProductId);
                            UserPartBid userPartBid = get(userPartBidPK);
                            userPartBid.setPrice(bidAmount);
                            em.persist(userPartBid);
                            bid = true;
                        } else {
                            UserPartBidPK userPartBidPK = new UserPartBidPK(userId, partProductId);
                            UserPartBid userPartBid = new UserPartBid(userPartBidPK);
                            userPartBid.setPrice(bidAmount);
                            em.persist(userPartBid);
                            bid = true;
                        }
                    }
                }
            }
        } else {
            throw new AuctionAlreadyClosedException();
        }

        return bid;
    }

    @Override
    public List<UserPartBid> loadAll() {
        return null;
    }

    @Override
    public void save(UserPartBid domain) {

    }

    @Override
    public void update(UserPartBid domain) {

    }

    @Override
    public void delete(UserPartBid domain) {

    }

    @Override
    public UserPartBid get(Serializable id) {
        return null;
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
