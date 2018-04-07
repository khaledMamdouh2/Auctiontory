package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.BatchProduct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addBatchBean")
@SessionScoped
public class AddBatchAuctionBean {
    @Inject
    private BatchAuctionController batchCtrl;
    @Inject
    private UserBean userBean; //to get owner from it

    private BatchAuction auctionAdded = new BatchAuction();
    private List<BatchProduct> auctionProducts = new ArrayList();
    private BatchProduct productAdded = new BatchProduct();

    public void addProduct(){
        auctionProducts.add(productAdded);
    }
    public void saveAuction(){

    }

    public BatchAuction getAuctionAdded() {
        return auctionAdded;
    }

    public void setAuctionAdded(BatchAuction auctionAdded) {
        this.auctionAdded = auctionAdded;
    }

    public BatchProduct getProductAdded() {
        return productAdded;
    }

    public void setProductAdded(BatchProduct productAdded) {
        this.productAdded = productAdded;
    }


}
