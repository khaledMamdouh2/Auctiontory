package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BatchControllerImpl implements BatchAuctionController {
    @Inject
    private BatchAuctionDAO batchAuctionDaoImpl;

    public List<BatchAuction> loadAll() {
        return batchAuctionDaoImpl.loadAll();
    }

    public void save(BatchAuction domain) {
        batchAuctionDaoImpl.save(domain);
    }

    public void update(BatchAuction domain) {
        batchAuctionDaoImpl.update(domain);
    }

    public void delete(BatchAuction domain) {
        batchAuctionDaoImpl.delete(domain);
    }

    public BatchAuction get(Serializable id) {
        return batchAuctionDaoImpl.get(id);
    }
}
