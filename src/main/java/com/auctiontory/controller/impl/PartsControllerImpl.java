package com.auctiontory.controller.impl;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.model.dal.PartsAuctionDAO;
import com.auctiontory.model.entity.PartsAuction;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@SessionScoped
public class PartsControllerImpl implements PartsAuctionController {
    @Inject
    private PartsAuctionDAO partsDao;

    public List<PartsAuction> loadAll() {
        return partsDao.loadAll();
    }

    public void save(PartsAuction domain) {
        partsDao.save(domain);
    }

    public void update(PartsAuction domain) {
        partsDao.update(domain);
    }

    public void delete(PartsAuction domain) {
        partsDao.delete(domain);
    }

    public PartsAuction get(Serializable id) {
        return partsDao.get(id);
    }
}
