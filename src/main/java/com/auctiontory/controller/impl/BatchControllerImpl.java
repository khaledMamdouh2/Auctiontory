package com.auctiontory.controller.impl;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.listener.BatchAuctionInterceptor;
import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.entity.BatchAuction;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.transaction.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BatchControllerImpl implements BatchAuctionController {
    @Inject
    private BatchAuctionDAO batchDao;

    @Inject
    BatchAuctionInterceptor batchAuctionInterceptor;

    @Resource
    private UserTransaction utx;

    public List<BatchAuction> loadAll() {
        List<BatchAuction> batchAuctions = null;
        try {
            utx.begin();
            batchAuctions = batchDao.loadAll();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return batchAuctions;
    }

    @Interceptors(BatchAuctionInterceptor.class)
    public void save(BatchAuction domain) {
        try {
            utx.begin();
            batchDao.save(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void update(BatchAuction domain) {
        try {
            utx.begin();
            batchDao.update(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void delete(BatchAuction domain) {
        try {
            utx.begin();
            batchDao.delete(domain);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }


    public BatchAuction get(Serializable id) {
        BatchAuction batchAuction = null;
        try {
            utx.begin();
            batchAuction = batchDao.get(id);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return batchAuction;
    }

    @Override
    public boolean isActive(Integer id) {
        boolean isActive = false;
        try {
            utx.begin();
            isActive = batchDao.isActive(id);
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

    @Override
    public boolean isExist(String auctionTitle) {
        boolean isExist = false;
        try {
            utx.begin();
            isExist = batchDao.isExist(auctionTitle);
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
}
