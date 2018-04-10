package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
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
        return em.find(BatchAuction.class, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
