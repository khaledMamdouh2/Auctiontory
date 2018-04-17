package com.auctiontory.view.bean;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.controller.PartsBidController;
import com.auctiontory.model.dal.exception.AlreadyHighestBidderException;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.PartsAuction;
import com.auctiontory.model.entity.PartsProduct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.inject.Inject;

@ManagedBean(name = "viewParts")
@SessionScoped
public class ViewPartsBean {

    @Inject
    private PartsAuction partsAuction;

    private PartsProduct partsProduct;

    private Integer partsProductId;

    private HtmlInputHidden htmlInputHidden;

    @Inject
    private PartsAuctionController partsAuctionController;

    @Inject
    private PartsBidController partsBidController;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    private boolean bidPast, bidTry;

    private String message;

    private Integer price;

    public void initJoined() {
        bidPast = partsBidController.alreadyBid(userBean.getUser().getId(), partsProduct.getId(), partsAuction.getId());
    }

    public PartsAuction getPartsAuction() {
        return partsAuction;
    }

    public void setPartsAuction(PartsAuction partsAuction) {
        this.partsAuction = partsAuction;
    }

    public PartsProduct getPartsProduct() {
        return partsProduct;
    }

    public void setPartsProduct(PartsProduct partsProduct) {
        this.partsProduct = partsProduct;
    }

    public PartsAuctionController getPartsAuctionController() {
        return partsAuctionController;
    }

    public void setPartsAuctionController(PartsAuctionController partsAuctionController) {
        this.partsAuctionController = partsAuctionController;
    }

    public PartsBidController getPartsBidController() {
        return partsBidController;
    }

    public void setPartsBidController(PartsBidController partsBidController) {
        this.partsBidController = partsBidController;
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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPrice() {
        if (price == null)
            price = new Integer(0);
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPartsProductId() {
        return partsProductId;
    }

    public void setPartsProductId(Integer partsProductId) {
        this.partsProductId = partsProductId;
    }

    public HtmlInputHidden getHtmlInputHidden() {
        return htmlInputHidden;
    }

    public void setHtmlInputHidden(HtmlInputHidden htmlInputHidden) {
        this.htmlInputHidden = htmlInputHidden;
        if (htmlInputHidden != null && htmlInputHidden.getValue() != null) {
            partsProductId = (Integer) htmlInputHidden.getValue();
        }
    }

    public String bid(int partsProductId) {
        partsAuction.getPartsProductList().forEach((a) -> {
            if (a.getId().equals(partsProductId)) {
                partsProduct = a;
            }
        });
        if (price == null || price == 0) {
            if (partsProduct.getHighestBid() == 0) {
                if (partsProduct.getMinBid() == 0) {
                    price = partsProduct.getMinBid() + 100;
                } else {

                    price = partsProduct.getMinBid();
                }
            } else {
                price = partsProduct.getHighestBid() + 100;
            }
        }
        try {
            bidTry = true;
            boolean successfulBid = partsBidController.bid(userBean.getUser().getId(), partsProduct.getId(), partsAuction.getId(), price);
            if (successfulBid) {
                message = "Successfully Bid";
            } else {
                message = "Couldn't bid, probably this is not the highest price, please retry";
            }

        } catch (AuctionAlreadyClosedException e) {
            message = "Sorry Auction is already closed";
        } catch (AlreadyHighestBidderException e) {
            message = "You are already highest bidder";
        }
        refreshAuction();
        return "viewParts";
    }

    public void refreshAuction() {
        partsAuction = partsAuctionController.get(partsAuction.getId());
    }
}
