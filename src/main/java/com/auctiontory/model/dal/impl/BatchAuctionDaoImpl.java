package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class BatchAuctionDaoImpl implements BatchAuctionDAO , Serializable{
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    public List<BatchAuction> loadAll() {
        return em.createNamedQuery("BatchAuction.findAll").getResultList();
    }

    public void save(BatchAuction domain) {
        em.persist(domain);
    }

    public void update(BatchAuction domain) {
        em.merge(domain);
    }

    public void delete(BatchAuction domain) {
        BatchAuction batchAuc = em.find(BatchAuction.class , domain.getId());
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
