package com.auctiontory.view.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "publicBean")
@ApplicationScoped
public class PublicBean {
    private String usedAuctionCurrently = "batch";

    public String getUsedAuctionCurrently() {
        return usedAuctionCurrently;
    }

    public void setUsedAuctionCurrently(String usedAuctionCurrently) {
        this.usedAuctionCurrently = usedAuctionCurrently;
    }
}
