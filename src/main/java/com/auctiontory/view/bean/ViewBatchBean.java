package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.BatchBidController;
import com.auctiontory.controller.impl.BatchControllerImpl;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "viewBatch")
@SessionScoped
public class ViewBatchBean {

    @Inject
    private BatchAuctionController batchController;

    @Inject
    private BatchBidController batchBidController;

    private BatchAuction batchAuction;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    @ManagedProperty(value = "#{viewBatchAuctionBean}")
    private ViewBatchAuctionBean viewBatchAuctionBean;

    private boolean bidPast, bidTry;

    private String message;

    public void initJoined() {
        bidPast = batchBidController.alreadyBid(userBean.getUser().getId(), batchAuction.getId());
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

    public boolean isBidPast() {
        return bidPast;
    }

    public void setBidPast(boolean bidPast) {
        this.bidPast = bidPast;
    }

    public boolean isBidTry() {
        return bidTry;
    }

    public void setBidTry(boolean bidTry) {
        this.bidTry = bidTry;
    }

    public String getMessage() {
        if (bidTry) {
            bidTry = false;
        } else {
            setMessage("");
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String bid() {
        int price;
        if (batchAuction.getHighestBid() == 0) {
            price = batchAuction.getMinBid();
        } else {
            price = batchAuction.getHighestBid() + 100;
        }
        try {
            bidTry = true;
            boolean bid = batchBidController.bid(userBean.getUser().getId(), batchAuction.getId(), price);

            if (bid) {
                message = "Successfully Bid";
                List<User> competitors = batchBidController.getOtherBidders(userBean.getUser().getId() , batchAuction.getId());
                if(competitors.size() > 0){
                    viewBatchAuctionBean.notifyAuctionCompetitors(competitors , batchAuction.getTitle());
                }
            } else {
                message = "Couldn't bid, probably this is not the highest price, please retry";
            }
        } catch (AuctionAlreadyClosedException e) {
            message = "Sorry Auction is already closed";
        } catch (AlreadyHighestBidderException e) {
            message = "You are already highest bidder";
        }
        refreshAuction();
        return "viewBatch";
    }

    public void refreshAuction() {
        batchAuction = batchController.get(batchAuction.getId());
    }
}
