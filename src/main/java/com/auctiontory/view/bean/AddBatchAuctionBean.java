package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.UserController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.BatchProduct;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addBatchBean")
@SessionScoped
public class AddBatchAuctionBean {
    @Inject
    private BatchAuctionController batchControllerImpl;
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    @Inject
    private UserController userControllerImpl;

    private BatchAuction auctionAdded = new BatchAuction();
    private List<BatchProduct> auctionProducts = new ArrayList();
    private BatchProduct productAdded = new BatchProduct();

    public String addProduct() {
        //Setting auction to the product
        productAdded.setAuctionId(auctionAdded);
        auctionProducts.add(productAdded);
        return null;
    }

    public String saveAuction() {
        addProduct(); //supposingly will be called from seperated form not from here
        User owner = userBean.getUser();
        if (owner != null) {
            String username = userBean.getUserName();
            String password = userBean.getPassword();
            User user = userControllerImpl.login(username, password);
            auctionAdded.setOwnerId(user);
            auctionAdded.setBatchProductList(auctionProducts);
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


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
