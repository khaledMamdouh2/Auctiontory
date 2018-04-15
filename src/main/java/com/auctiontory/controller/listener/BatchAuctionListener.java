package com.auctiontory.controller.listener;


import com.auctiontory.view.bean.ViewBatchAuctionBean;

import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class BatchAuctionListener {
    @Inject
    private ViewBatchAuctionBean viewBatchAuctionBean;

    public BatchAuctionListener() {
    }

    public void upadeView(Object o) {
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
