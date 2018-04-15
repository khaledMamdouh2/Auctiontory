package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.PartsAuctionDAO;
import com.auctiontory.model.entity.PartsAuction;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("partsDao")
public class PartsAuctionDaoImpl implements PartsAuctionDAO , Serializable{
    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    public List<PartsAuction> loadAll() {
        return em.createNamedQuery("PartsAuction.findAll").getResultList();
    }

    public void save(PartsAuction domain) {
        em.persist(domain);
    }

    public void update(PartsAuction domain) {
        em.merge(domain);
    }

    public void delete(PartsAuction domain) {
        PartsAuction partsAuc = em.find(PartsAuction.class , domain.getId());
        em.remove(partsAuc);
    }

    public PartsAuction get(Serializable id) {
        return em.find(PartsAuction.class , id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public boolean isExist(String auctionTitle){
        Query query = em.createNamedQuery("PartsAuction.findByTitle");
        List auc =  query.setParameter("title",auctionTitle).getResultList();
        return auc.size()==0? false : true;
    }
}
