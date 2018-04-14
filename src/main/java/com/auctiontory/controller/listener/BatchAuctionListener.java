package com.auctiontory.controller.listener;


import com.auctiontory.view.bean.ViewBatchAuctionBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
