package com.auctiontory.controller.listener;


import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.BatchBidController;
import com.auctiontory.controller.impl.BatchControllerImpl;
import com.auctiontory.model.dal.BatchAuctionDAO;
import com.auctiontory.model.dal.BatchBidDAO;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.User;
import com.auctiontory.view.bean.UserBean;
import com.auctiontory.view.bean.ViewBatchAuctionBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.List;

public class BatchAuctionInterceptor {
    @Inject
    private ViewBatchAuctionBean viewBatchAuctionBean;

    @Inject
    private BatchBidDAO batchBidDao;

    @Inject
    private BatchAuctionController batchAuctionCtrl;



    public BatchAuctionInterceptor() {
    }

    @AroundInvoke
    public Object updateView(InvocationContext joinPoint) throws Exception {
        Object ret = joinPoint.proceed();
        if (viewBatchAuctionBean != null) {
            if (joinPoint.getMethod().getName().equals("bid")) {
                viewBatchAuctionBean.notifyUpdate();
                ////notification//////
                Integer userId = (Integer) joinPoint.getParameters()[0];
                Integer auctionId = (Integer) joinPoint.getParameters()[1];
                List<User> competitors = batchBidDao.getOtherBidders(userId , auctionId);
                if(competitors.size() > 0){
                    BatchAuction auction = batchAuctionCtrl.get(auctionId);
                    viewBatchAuctionBean.notifyAuctionCompetitors(competitors , auction.getTitle());
                }
            }
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
