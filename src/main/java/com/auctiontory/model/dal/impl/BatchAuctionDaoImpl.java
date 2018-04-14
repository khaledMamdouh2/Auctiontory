package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserBatchBid;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Dependent
@Named("batchDao")
public class BatchAuctionDaoImpl implements BatchAuctionDAO, Serializable {
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    public List<BatchAuction> loadAll() {
        List<BatchAuction> batchAuctions = em.createNamedQuery("BatchAuction.findAll").getResultList();

        // Bad solution to force loading of bids and products
        for (BatchAuction a : batchAuctions) {
            if (a.getBatchProductList() != null)
                a.getBatchProductList().size();
            if (a.getUserBatchBidList() != null)
                a.getUserBatchBidList().size();
        }
        batchAuctions.forEach(auction -> {
            auction.setActive(isActive(auction.getId()));
            Integer highestBid = 0;
            User highestBidderId = null;
            if (auction.getUserBatchBidList() != null) {
                auction.setNumberOfBids(auction.getUserBatchBidList().size());
                for (UserBatchBid userBatchBid : auction.getUserBatchBidList()) {
                    if (userBatchBid.getPrice() > highestBid) {
                        highestBid = userBatchBid.getPrice();
                        highestBidderId = userBatchBid.getUser();
                    }
                }
            }
            if (highestBidderId != null) {
                auction.setHighestBidderId(highestBidderId);
            }
            auction.setHighestBid(highestBid);
        });
        return batchAuctions;
    }

    public void save(BatchAuction domain) {
        em.persist(domain);
    }

    public void update(BatchAuction domain) {
        em.merge(domain);
    }

    public void delete(BatchAuction domain) {
        BatchAuction batchAuc = em.find(BatchAuction.class, domain.getId());
        em.remove(batchAuc);
    }

    public BatchAuction get(Serializable id) {
        BatchAuction batchAuction = em.find(BatchAuction.class, id);
        batchAuction.setActive(isActive((Integer) id));
        if (batchAuction.getBatchProductList() != null)
            batchAuction.getBatchProductList().size();
        if (batchAuction.getUserBatchBidList() != null) {
            batchAuction.getUserBatchBidList().size();
            Collections.sort(batchAuction.getUserBatchBidList());
        }

        Integer highestBid = 0;
        User highestBidderId = null;
        if (batchAuction.getUserBatchBidList() != null) {
            batchAuction.setNumberOfBids(batchAuction.getUserBatchBidList().size());
            for (UserBatchBid userBatchBid : batchAuction.getUserBatchBidList()) {
                if (userBatchBid.getPrice() > highestBid) {
                    highestBid = userBatchBid.getPrice();
                    highestBidderId = userBatchBid.getUser();
                }
            }
        }
        if (highestBidderId != null) {
            batchAuction.setHighestBidderId(highestBidderId);
        }
        batchAuction.setHighestBid(highestBid);
        return batchAuction;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public boolean isActive(Integer id) {
        boolean isActive = false;
        Date nowDate = new Date();
        Date auctionDate = null;
        BatchAuction batchAuction = em.find(BatchAuction.class, id);
        auctionDate = batchAuction.getDeadline();
        isActive = nowDate.before(auctionDate);
        return isActive;
    }
}
