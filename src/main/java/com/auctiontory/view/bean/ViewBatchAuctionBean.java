package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserBatchBid;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.*;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class ViewBatchAuctionBean {
    @Inject
    private BatchAuctionController batchControllerImpl;

    @Inject
    @Push
    private PushContext auctionsChannel;

    private ArrayList<BatchAuction> batchAuctions;

    @PostConstruct
    public void fillAuctions() {
        batchAuctions = (ArrayList<BatchAuction>) batchControllerImpl.loadAll();
        batchAuctions.forEach(auction -> {
            Integer highestBid = 0;
            User highestBidderId = null;
            for (UserBatchBid userBatchBid : auction.getUserBatchBidList()) {
                if (userBatchBid.getPrice() > highestBid) {
                    highestBid = userBatchBid.getPrice();
                    highestBidderId = userBatchBid.getUser();
                }
            }
            if (highestBidderId != null) {
                auction.setHighestBid(highestBid);
                auction.setHighestBidderId(highestBidderId);
            } else {

            }
        });
    }

    public ArrayList<BatchAuction> getBatchAuctions() {
        return batchAuctions;
    }

    public void setBatchAuctions(ArrayList<BatchAuction> batchAuctions) {
        this.batchAuctions = batchAuctions;
    }

    public void notifyUpdate() {
        fillAuctions();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (BatchAuction batchAuction : batchAuctions) {
            jsonArrayBuilder.add(batchAuction.toString());
        }
        JsonArray auctions = jsonArrayBuilder.build();
        auctionsChannel.send(auctions);
    }

    public BatchAuctionController getBatchControllerImpl() {
        return batchControllerImpl;
    }

    public void setBatchControllerImpl(BatchAuctionController batchControllerImpl) {
        this.batchControllerImpl = batchControllerImpl;
    }
}
