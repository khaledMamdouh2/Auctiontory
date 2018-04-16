package com.auctiontory.view.bean;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.model.entity.PartsAuction;
import com.google.gson.Gson;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputHidden;
import javax.inject.Inject;
import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ViewPartsAuctionBean {
    @Inject
    private PartsAuctionController partsControllerImpl;

    @Inject
    @Push
    private PushContext partsAuctionsChannel;

    private ArrayList<PartsAuction> partsAuctions;

    private HtmlInputHidden partIdInput;

    @ManagedProperty("#{viewPartsBean}")
    private ViewPartsBean viewPartsBean;

    private Integer partsId;

    @PostConstruct
    public void fillAuctions() {
        partsAuctions = (ArrayList<PartsAuction>) partsControllerImpl.loadAll();
    }

    public ArrayList<PartsAuction> getPartsAuctions() {
        return partsAuctions;
    }

    public void setPartsAuctions(ArrayList<PartsAuction> partsAuctions) {
        this.partsAuctions = partsAuctions;
    }

    public void notifyAddAuction(PartsAuction partsAuction) {
        Gson gson = new Gson();
        String auctionStr = gson.toJson(new PartsAuction(partsAuction));
        partsAuctionsChannel.send(auctionStr);
    }

    public PartsAuctionController getPartsControllerImpl() {
        return partsControllerImpl;
    }

    public void setPartsControllerImpl(PartsAuctionController partsControllerImpl) {
        this.partsControllerImpl = partsControllerImpl;
    }

    public Integer getPartsId() {
        return partsId;
    }

    public void setPartsId(Integer partsId) {
        this.partsId = partsId;
    }

    public HtmlInputHidden getPartIdInput() {
        return partIdInput;
    }

    public void setPartIdInput(HtmlInputHidden partIdInput) {
        this.partIdInput = partIdInput;
        this.partsId = (Integer) this.partIdInput.getValue();
    }

    public ViewPartsBean getViewPartsBean() {
        return viewPartsBean;
    }

    public void setViewPartsBean(ViewPartsBean viewPartsBean) {
        this.viewPartsBean = viewPartsBean;
    }

    public String visitAuctionDetails() {
        PartsAuction partsAuction = partsControllerImpl.get(partsId);
        viewPartsBean.setPartsAuction(partsAuction);
        return "viewParts";
    }

    public String visitAuctionDetailsNormally(int auctionId) {
        this.partsId = auctionId;
        return visitAuctionDetails();
    }
}