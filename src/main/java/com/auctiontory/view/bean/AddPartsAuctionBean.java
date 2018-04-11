package com.auctiontory.view.bean;

import com.auctiontory.controller.PartsAuctionController;
import com.auctiontory.controller.UserController;
import com.auctiontory.model.entity.PartsAuction;
import com.auctiontory.model.entity.PartsProduct;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addPartsBean")
@SessionScoped
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

    private String msg , errMsg;
    private int productsNumber;

    public String addProduct() {
        auctionProducts.add(productAdded);
        productAdded = new PartsProduct();
        return "addAuction";
    }


    public String saveAuction() {
        if(auctionProducts.size()>0) {

            for(PartsProduct p : auctionProducts){
                p.setAuctionId(auctionAdded);
            }

            String username = userBean.getUserName();
            String password = userBean.getPassword();

            if (username != null && password != null) {
                User user = userControllerImpl.login(username, password);
                auctionAdded.setOwnerId(user);
                auctionAdded.setPartsProductList(auctionProducts);
                partsControllerImpl.save(auctionAdded);
                msg = "auction added successfully";
                /////resetting data////
                errMsg = null;
                auctionAdded = new PartsAuction();
                productAdded = new PartsProduct();
                auctionProducts = new ArrayList<>();
            }

        }
        else{
            errMsg = "sorry, you must add product at least to the auction";
            msg = null;
        }
        return "addAuction";
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getProductsNumber() {
        productsNumber = auctionProducts.size();
        return productsNumber;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }
}
