/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author mahrous
 */
@Entity
@Table(name = "user_part_bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPartBid.findAll", query = "SELECT u FROM UserPartBid u")
    , @NamedQuery(name = "UserPartBid.findByUserId", query = "SELECT u FROM UserPartBid u WHERE u.userPartBidPK.userId = :userId")
    , @NamedQuery(name = "UserPartBid.findByPartProductId", query = "SELECT u FROM UserPartBid u WHERE u.userPartBidPK.partProductId = :partProductId")
    , @NamedQuery(name = "UserPartBid.findByPrice", query = "SELECT u FROM UserPartBid u WHERE u.price = :price")})
public class UserPartBid implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserPartBidPK userPartBidPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @JoinColumn(name = "part_product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PartsProduct partsProduct;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserPartBid() {
    }

    public UserPartBid(UserPartBidPK userPartBidPK) {
        this.userPartBidPK = userPartBidPK;
    }

    public UserPartBid(UserPartBidPK userPartBidPK, int price) {
        this.userPartBidPK = userPartBidPK;
        this.price = price;
    }

    public UserPartBid(int userId, int partProductId) {
        this.userPartBidPK = new UserPartBidPK(userId, partProductId);
    }

    public UserPartBidPK getUserPartBidPK() {
        return userPartBidPK;
    }

    public void setUserPartBidPK(UserPartBidPK userPartBidPK) {
        this.userPartBidPK = userPartBidPK;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PartsProduct getPartsProduct() {
        return partsProduct;
    }

    public void setPartsProduct(PartsProduct partsProduct) {
        this.partsProduct = partsProduct;
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
        hash += (userPartBidPK != null ? userPartBidPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPartBid)) {
            return false;
        }
        UserPartBid other = (UserPartBid) object;
        if ((this.userPartBidPK == null && other.userPartBidPK != null) || (this.userPartBidPK != null && !this.userPartBidPK.equals(other.userPartBidPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.UserPartBid[ userPartBidPK=" + userPartBidPK + " ]";
    }
    
}
