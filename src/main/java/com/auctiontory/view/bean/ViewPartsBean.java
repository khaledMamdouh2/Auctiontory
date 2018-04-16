package com.auctiontory.view.bean;

import com.auctiontory.model.entity.PartsAuction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class ViewPartsBean {

    private PartsAuction partsAuction;

    public PartsAuction getPartsAuction() {
        return partsAuction;
    }

    public void setPartsAuction(PartsAuction partsAuction) {
        this.partsAuction = partsAuction;
    }
}
