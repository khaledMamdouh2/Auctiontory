package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.PartsAuctionDAO;
import com.auctiontory.model.entity.PartsAuction;
import com.auctiontory.model.entity.PartsProduct;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserPartBid;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Dependent
@Named("partsDao")
public class PartsAuctionDaoImpl implements PartsAuctionDAO, Serializable {
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    public List<PartsAuction> loadAll() {

        List<PartsAuction> partsAuctions = em.createNamedQuery("PartsAuction.findAll").getResultList();

        for (PartsAuction partsAuction : partsAuctions) {
            if (partsAuction.getPartsProductList() != null && partsAuction.getPartsProductList().size() > 0) {
                List<PartsProduct> partsProducts = partsAuction.getPartsProductList();
                for (PartsProduct partsProduct : partsProducts) {
                    Integer highestBid = 0;
                    User highestBidderId = null;

                    List<UserPartBid> userPartBids = partsProduct.getUserPartBidList();
                    if (userPartBids != null && userPartBids.size() > 0) {
                        partsProduct.setNumberOfBids(userPartBids.size());
                        for (UserPartBid userPartBid : userPartBids) {
                            if (userPartBid.getPrice() > highestBid) {
                                highestBid = userPartBid.getPrice();
                                highestBidderId = userPartBid.getUser();
                            }
                        }
                    }
                    if (highestBidderId != null) {
                        partsProduct.setHighestBidderId(highestBidderId);
                    }
                    partsProduct.setHighestBid(highestBid);
                }
            }
        }

        partsAuctions.forEach(auction -> {
            auction.setActive(isActive(auction.getId()));
        });

        return partsAuctions;
    }

    public void save(PartsAuction domain) {
        em.persist(domain);
    }

    public void update(PartsAuction domain) {
        em.merge(domain);
    }

    public void delete(PartsAuction domain) {
        PartsAuction partsAuc = em.find(PartsAuction.class, domain.getId());
        em.remove(partsAuc);
    }

    public PartsAuction get(Serializable id) {
        PartsAuction partsAuction = em.find(PartsAuction.class, id);
        if (partsAuction.getPartsProductList() != null && partsAuction.getPartsProductList().size() > 0) {
            List<PartsProduct> partsProducts = partsAuction.getPartsProductList();
            for (PartsProduct partsProduct : partsProducts) {
                Integer highestBid = 0;
                User highestBidderId = null;

                List<UserPartBid> userPartBids = partsProduct.getUserPartBidList();
                if (userPartBids != null && userPartBids.size() > 0) {
                    partsProduct.setNumberOfBids(userPartBids.size());
                    for (UserPartBid userPartBid : userPartBids) {
                        if (userPartBid.getPrice() > highestBid) {
                            highestBid = userPartBid.getPrice();
                            highestBidderId = userPartBid.getUser();
                        }
                    }
                }
                if (highestBidderId != null) {
                    partsProduct.setHighestBidderId(highestBidderId);
                }
                partsProduct.setHighestBid(highestBid);
            }
        }
        partsAuction.setActive(isActive(partsAuction.getId()));
        return partsAuction;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public boolean isExist(String auctionTitle) {
        Query query = em.createNamedQuery("PartsAuction.findByTitle");
        List auc = query.setParameter("title", auctionTitle).getResultList();
        return auc.size() == 0 ? false : true;
    }

    @Override
    public boolean isActive(Integer id) {
        boolean isActive = false;
        Date nowDate = new Date();
        Date auctionDate = null;
        PartsAuction partsAuction = em.find(PartsAuction.class, id);
        auctionDate = partsAuction.getDeadline();
        isActive = nowDate.before(auctionDate);
        return isActive;
    }
}
