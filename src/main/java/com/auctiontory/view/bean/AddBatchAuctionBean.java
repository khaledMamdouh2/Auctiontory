package com.auctiontory.view.bean;

import com.auctiontory.controller.BatchAuctionController;
import com.auctiontory.controller.UserController;
import com.auctiontory.model.entity.BatchAuction;
import com.auctiontory.model.entity.BatchProduct;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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

    private String msg , errMsg;
    private int productsNumber;


    public String addProduct() {

        auctionProducts.add(productAdded);
        productAdded = new BatchProduct();
        errMsg = null;
        return null;
    }

    public String saveAuction() {
        if(auctionProducts.size()>0) {

            for (BatchProduct p : auctionProducts) {
                p.setAuctionId(auctionAdded);
            }
            String username = userBean.getUserName();
            String password = userBean.getPassword();

            if (username != null && password != null) {

                User user = userControllerImpl.login(username, password);
                auctionAdded.setOwnerId(user);
                auctionAdded.setBatchProductList(auctionProducts);
                batchControllerImpl.save(auctionAdded);
                msg = "auction added successfully";
                /////resetting data////
                errMsg = null;
                auctionAdded = new BatchAuction();
                productAdded = new BatchProduct();
                auctionProducts = new ArrayList<>();
            }
            else {
                return "home";
            }
        }
        else{
            errMsg = "sorry, you must add product at least to the auction";
            msg = null;
        }
        return "addAuction";
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
