/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import com.auctiontory.controller.listener.BatchAuctionInterceptor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author mahrous
 */
@Entity
@Table(name = "user_batch_bid")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UserBatchBid.findAll", query = "SELECT u FROM UserBatchBid u")
        , @NamedQuery(name = "UserBatchBid.findByUserId", query = "SELECT u FROM UserBatchBid u WHERE u.userBatchBidPK.userId = :userId")
        , @NamedQuery(name = "UserBatchBid.findByBatchId", query = "SELECT u FROM UserBatchBid u WHERE u.userBatchBidPK.batchId = :batchId")
        , @NamedQuery(name = "UserBatchBid.findByBatchIdAndUserId", query = "SELECT u FROM UserBatchBid u WHERE u.userBatchBidPK.batchId = :batchId and u.userBatchBidPK.userId = :userId")
        , @NamedQuery(name = "UserBatchBid.findByPrice", query = "SELECT u FROM UserBatchBid u WHERE u.price = :price")})
@EntityListeners(BatchAuctionInterceptor.class)
public class UserBatchBid implements Serializable, Comparable<UserBatchBid> {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserBatchBidPK userBatchBidPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @JoinColumn(name = "batch_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BatchAuction batchAuction;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserBatchBid() {
    }

    public UserBatchBid(UserBatchBidPK userBatchBidPK) {
        this.userBatchBidPK = userBatchBidPK;
    }

    public UserBatchBid(UserBatchBidPK userBatchBidPK, int price) {
        this.userBatchBidPK = userBatchBidPK;
        this.price = price;
    }

    public UserBatchBid(int userId, int batchId) {
        this.userBatchBidPK = new UserBatchBidPK(userId, batchId);
    }

    public UserBatchBidPK getUserBatchBidPK() {
        return userBatchBidPK;
    }

    public void setUserBatchBidPK(UserBatchBidPK userBatchBidPK) {
        this.userBatchBidPK = userBatchBidPK;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BatchAuction getBatchAuction() {
        return batchAuction;
    }

    public void setBatchAuction(BatchAuction batchAuction) {
        this.batchAuction = batchAuction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userBatchBidPK != null ? userBatchBidPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserBatchBid)) {
            return false;
        }
        UserBatchBid other = (UserBatchBid) object;
        if ((this.userBatchBidPK == null && other.userBatchBidPK != null) || (this.userBatchBidPK != null && !this.userBatchBidPK.equals(other.userBatchBidPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.UserBatchBid[ userBatchBidPK=" + userBatchBidPK + " ]";
    }

    @Override
    public int compareTo(UserBatchBid o) {
        return o.price - this.price;
    }
}
