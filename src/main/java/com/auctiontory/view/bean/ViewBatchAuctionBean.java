package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.BatchBidController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserBatchBid;
import com.google.gson.Gson;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class ViewBatchAuctionBean {
    @Inject
    private BatchAuctionController batchControllerImpl;

    @Inject
    private BatchBidController batchBidControllerImpl;


    @Inject
    @Push
    private PushContext auctionsChannel;

    private ArrayList<BatchAuction> batchAuctions;

    @PostConstruct
    public void fillAuctions() {
        batchAuctions = (ArrayList<BatchAuction>) batchControllerImpl.loadAll();
    }

    public ArrayList<BatchAuction> getBatchAuctions() {
        return batchAuctions;
    }

    public void setBatchAuctions(ArrayList<BatchAuction> batchAuctions) {
        this.batchAuctions = batchAuctions;
    }

    public void notifyUpdate() {
        fillAuctions();
        Gson gson = new Gson();
        ArrayList<BatchAuction> batchAuctionsGson = new ArrayList<>();
        for (BatchAuction batchAuction : batchAuctions) {
            batchAuctionsGson.add(new BatchAuction(batchAuction));
        }
        String auctionsStr = gson.toJson(batchAuctionsGson);
        auctionsChannel.send(auctionsStr);
    }

    public BatchAuctionController getBatchControllerImpl() {
        return batchControllerImpl;
    }

    public void setBatchControllerImpl(BatchAuctionController batchControllerImpl) {
        this.batchControllerImpl = batchControllerImpl;
    }

    public void testBid() {
        batchBidControllerImpl.bid(8, 2, 8000);
    }
}
