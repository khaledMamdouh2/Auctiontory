/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author mahrous
 */
@Embeddable
public class UserPartBidPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "part_product_id")
    private int partProductId;

    public UserPartBidPK() {
    }

    public UserPartBidPK(int userId, int partProductId) {
        this.userId = userId;
        this.partProductId = partProductId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPartProductId() {
        return partProductId;
    }

    public void setPartProductId(int partProductId) {
        this.partProductId = partProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) partProductId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPartBidPK)) {
            return false;
        }
        UserPartBidPK other = (UserPartBidPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.partProductId != other.partProductId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.UserPartBidPK[ userId=" + userId + ", partProductId=" + partProductId + " ]";
    }
    
}
