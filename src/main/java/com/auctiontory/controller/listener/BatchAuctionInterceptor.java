package com.auctiontory.controller.listener;


import com.auctiontory.controller.impl.BatchControllerImpl;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.view.bean.ViewBatchAuctionBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BatchAuctionInterceptor {
    @Inject
    private ViewBatchAuctionBean viewBatchAuctionBean;

    public BatchAuctionInterceptor() {
    }

    @AroundInvoke
    public Object updateView(InvocationContext joinPoint) throws Exception {
        Object ret = joinPoint.proceed();
        if (viewBatchAuctionBean != null) {
            if (joinPoint.getMethod().getName().equals("bid"))
                viewBatchAuctionBean.notifyUpdate();
            else if (joinPoint.getMethod().getName().equals("save") && joinPoint.getTarget().getClass().equals(BatchControllerImpl.class)) {
                BatchAuction batchAuction = (BatchAuction) joinPoint.getParameters()[0];
                viewBatchAuctionBean.notifyAddAuction(batchAuction);
            }
        }
        return ret;
    }

    public ViewBatchAuctionBean getViewBatchAuctionBean() {
        return viewBatchAuctionBean;
    }

    public void setViewBatchAuctionBean(ViewBatchAuctionBean viewBatchAuctionBean) {
        this.viewBatchAuctionBean = viewBatchAuctionBean;
    }

}
