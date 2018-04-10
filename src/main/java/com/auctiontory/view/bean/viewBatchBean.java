package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.BatchBidController;
import com.auctiontory.controller.impl.BatchControllerImpl;
import com.auctiontory.model.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "viewBatch")
@SessionScoped
public class viewBatchBean {

    @Inject
    private BatchAuctionController batchController;

    @Inject
    private BatchBidController batchBidController;

    private BatchAuction batchAuction;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;


    private boolean joined = false;

    public viewBatchBean(){

    }

    public void setBatchController(BatchAuctionController batchController) {
        this.batchController = batchController;
    }

    public BatchBidController getBatchBidController() {
        return batchBidController;
    }

    public void setBatchBidController(BatchBidController batchBidController) {
        this.batchBidController = batchBidController;
    }

    public BatchAuction getBatchAuction() {

        batchAuction = batchController.loadAll().get(0);
        return batchAuction;
    }

    public void setBatchAuction(BatchAuction batchAuction) {
        this.batchAuction = batchAuction;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public void joinAuction(){
        if(batchBidController.alreadyBid(userBean.getUser().getId(),batchAuction.getId()))
        {
            setJoined(true);
        }

    }

    public String auctionBid(int price){
        int userId = userBean.getUser().getId();
        int batchAuctionId = batchAuction.getId();
        boolean correctBid = batchBidController.bid(userId, batchAuctionId,price);
        if(!correctBid){
           return "wrong amount";
        }else{
            return "bid correct";
        }
    }
}
