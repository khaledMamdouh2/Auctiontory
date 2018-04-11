package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.BatchBidController;
import com.auctiontory.model.dal.exception.AuctionAlreadyClosedException;
import com.auctiontory.model.entity.BatchAuction;
import com.google.gson.Gson;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ViewBatchAuctionBean {
    @Inject
    private BatchAuctionController batchControllerImpl;

    @Inject
    private BatchBidController batchBidControllerImpl;


    @ManagedProperty(value = "#{viewBatch}")
    private ViewBatchBean viewBatchBean;

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

    public ViewBatchBean getViewBatchBean() {
        return viewBatchBean;
    }

    public void setViewBatchBean(ViewBatchBean viewBatchBean) {
        this.viewBatchBean = viewBatchBean;
    }

    public void testBid() {
        try {
            batchBidControllerImpl.bid(8, 2, 8000);
        } catch (AuctionAlreadyClosedException e) {
            e.printStackTrace();
        }
    }

    public String visitAuctionDetails(Integer batchId) {
        BatchAuction batchAuction = batchControllerImpl.get(batchId);
        viewBatchBean.setBatchAuction(batchAuction);
        viewBatchBean.initJoined();
        return "viewBatch";
    }
}
