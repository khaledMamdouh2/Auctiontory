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
public class UserBatchBidPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "batch_id")
    private int batchId;

    public UserBatchBidPK() {
    }

    public UserBatchBidPK(int userId, int batchId) {
        this.userId = userId;
        this.batchId = batchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) batchId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserBatchBidPK)) {
            return false;
        }
        UserBatchBidPK other = (UserBatchBidPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.batchId != other.batchId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.UserBatchBidPK[ userId=" + userId + ", batchId=" + batchId + " ]";
    }
    
}
