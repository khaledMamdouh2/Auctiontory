package com.auctiontory.controller.listener;


import com.auctiontory.controller.impl.BatchControllerImpl;
import com.auctiontory.controller.impl.PartsControllerImpl;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.PartsAuction;
import com.auctiontory.view.bean.ViewBatchAuctionBean;
import com.auctiontory.view.bean.ViewPartsAuctionBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PartAuctionInterceptor {
    @Inject
    private ViewPartsAuctionBean viewPartsAuctionBean;

    public PartAuctionInterceptor() {
    }

    @AroundInvoke
    public Object updateView(InvocationContext joinPoint) throws Exception {
        Object ret = joinPoint.proceed();
        if (viewPartsAuctionBean != null) {
            if (joinPoint.getMethod().getName().equals("bid"))
                viewPartsAuctionBean.notifyUpdate();
            else if (joinPoint.getMethod().getName().equals("save") && joinPoint.getTarget().getClass().equals(PartsControllerImpl.class)) {
                PartsAuction partsAuction = (PartsAuction) joinPoint.getParameters()[0];
                viewPartsAuctionBean.notifyAddAuction(partsAuction);
            }
        }
        return ret;
    }

    public ViewPartsAuctionBean getViewPartsAuctionBean() {
        return viewPartsAuctionBean;
    }

    public void setViewPartsAuctionBean(ViewPartsAuctionBean viewPartsAuctionBean) {
        this.viewPartsAuctionBean = viewPartsAuctionBean;
    }
}
