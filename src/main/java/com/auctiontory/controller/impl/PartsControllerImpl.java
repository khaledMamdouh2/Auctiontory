package com.auctiontory.controller.impl;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.controller.listener.BatchAuctionInterceptor;
import com.auctiontory.controller.listener.PartAuctionInterceptor;
import com.auctiontory.model.dal.PartsAuctionDAO;
import com.auctiontory.model.entity.PartsAuction;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PartsControllerImpl implements PartsAuctionController {
    @Inject
    private PartsAuctionDAO partsDao;

    @Resource
    private UserTransaction utx;

    public List<PartsAuction> loadAll() {
        List<PartsAuction> partsAuctions = null;
        try {
            utx.begin();
            partsAuctions = partsDao.loadAll();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return partsAuctions;
    }

    @Interceptors(PartAuctionInterceptor.class)
    public void save(PartsAuction domain) {
        try {
            utx.begin();
            partsDao.save(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void update(PartsAuction domain) {
        try {
            utx.begin();
            partsDao.update(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void delete(PartsAuction domain) {
        try {
            utx.begin();
            partsDao.delete(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public PartsAuction get(Serializable id) {
        PartsAuction partsAuctions = null;
        try {
            utx.begin();
            partsAuctions = partsDao.get(id);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return partsAuctions;
    }

    @Override
    public boolean isExist(String auctionTitle) {
        boolean isExist = false;
        try {
            utx.begin();
            partsDao.isExist(auctionTitle);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return isExist;
    }

    @Override
    public boolean isActive(Integer id) {
        boolean isActive = false;
        try {
            utx.begin();
            partsDao.isActive(id);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return isActive;
    }
}
