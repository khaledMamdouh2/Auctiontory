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
public class PartsControllerImpl implements PartsAuctionController {
    @Inject
    private PartsAuctionDAO partsAuctionDaoImpl;

    public List<PartsAuction> loadAll() {
        return partsAuctionDaoImpl.loadAll();
    }

    public void save(PartsAuction domain) {
        partsAuctionDaoImpl.save(domain);
    }

    public void update(PartsAuction domain) {
        partsAuctionDaoImpl.update(domain);
    }

    public void delete(PartsAuction domain) {
        partsAuctionDaoImpl.delete(domain);
    }

    public PartsAuction get(Serializable id) {
        return partsAuctionDaoImpl.get(id);
    }
}
