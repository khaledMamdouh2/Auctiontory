package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.BatchProduct;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addBatchBean")
@SessionScoped
public class AddBatchAuctionBean {
    @Inject
    private BatchAuctionController batchControllerImpl;
    @Inject
    private UserBean userBean; //to get owner from it

    private BatchAuction auctionAdded = new BatchAuction();
    private List<BatchProduct> auctionProducts = new ArrayList();
    private BatchProduct productAdded = new BatchProduct();

    public String addProduct(){
        auctionProducts.add(productAdded);
        return null;
    }
    public String saveAuction(){
        addProduct(); //supposingly will be called from seperated form not from here
        User owner = userBean.getUser();
        if(owner != null){
            auctionAdded.setOwnerId(owner);
            batchControllerImpl.save(auctionAdded);

        }
        return null;
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
