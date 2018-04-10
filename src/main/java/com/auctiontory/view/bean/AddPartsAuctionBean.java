package com.auctiontory.view.bean;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.controller.UserController;
import com.auctiontory.model.entity.PartsAuction;
import com.auctiontory.model.entity.PartsProduct;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addPartsBean")
@RequestScoped
public class AddPartsAuctionBean {
    @Inject
    private PartsAuctionController partsControllerImpl;
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    @Inject
    private UserController userControllerImpl;

    private PartsAuction auctionAdded = new PartsAuction();
    private List<PartsProduct> auctionProducts = new ArrayList();
    private PartsProduct productAdded = new PartsProduct();

    public String addProduct() {
        //Setting auction to the product
        productAdded.setAuctionId(auctionAdded);
        auctionProducts.add(productAdded);
        return null;
    }


    public String saveAuction() {
        addProduct(); //supposingly will be called from seperated form not from here
        String username = userBean.getUserName();
        String password = userBean.getPassword();

        if (username != null && password != null) {
            User user = userControllerImpl.login(username, password);
            auctionAdded.setOwnerId(user);
            auctionAdded.setPartsProductList(auctionProducts);
            partsControllerImpl.save(auctionAdded);
        }
        return null;
    }

    public PartsAuction getAuctionAdded() {
        return auctionAdded;
    }

    public void setAuctionAdded(PartsAuction auctionAdded) {
        this.auctionAdded = auctionAdded;
    }

    public PartsProduct getProductAdded() {
        return productAdded;
    }

    public void setProductAdded(PartsProduct productAdded) {
        this.productAdded = productAdded;
    }
}
