package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.listener.BatchAuctionListener;
import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BatchControllerImpl implements BatchAuctionController {
    @Inject
    private BatchAuctionDAO batchDao;

    @Inject
    BatchAuctionListener batchAuctionListener;

    public List<BatchAuction> loadAll() {
        return batchDao.loadAll();
    }

    public void save(BatchAuction domain) {
        batchDao.save(domain);
    }

    public void update(BatchAuction domain) {
        batchDao.update(domain);
    }

    public void delete(BatchAuction domain) {
        batchDao.delete(domain);
    }


    public BatchAuction get(Serializable id) {
        return batchDao.get(id);
    }

    @Override
    public boolean isActive(Integer id) {
        return batchDao.isActive(id);
    }

    @Override
    public boolean isExist(String auctionTitle){
        return batchDao.isExist(auctionTitle);
    }
}
