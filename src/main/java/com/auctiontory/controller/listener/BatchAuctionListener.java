package com.auctiontory.controller.listener;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.view.bean.ViewBatchAuctionBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class BatchAuctionListener {
    @Inject
    private ViewBatchAuctionBean viewBatchAuctionBean;

    public BatchAuctionListener() {
    }

    public void upadeView() {
        if (viewBatchAuctionBean != null) {
                viewBatchAuctionBean.notifyUpdate();
        }
    }

    public ViewBatchAuctionBean getViewBatchAuctionBean() {
        return viewBatchAuctionBean;
    }

    public void setViewBatchAuctionBean(ViewBatchAuctionBean viewBatchAuctionBean) {
        this.viewBatchAuctionBean = viewBatchAuctionBean;
    }

}
