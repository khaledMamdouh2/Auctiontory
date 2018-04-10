/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import com.auctiontory.controller.listener.BatchAuctionListener;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mahrous
 */
@Entity
@Table(name = "batch_auction")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "BatchAuction.findAll", query = "SELECT b FROM BatchAuction b")
        , @NamedQuery(name = "BatchAuction.findById", query = "SELECT b FROM BatchAuction b WHERE b.id = :id")
        , @NamedQuery(name = "BatchAuction.findByDeadline", query = "SELECT b FROM BatchAuction b WHERE b.deadline = :deadline")
        , @NamedQuery(name = "BatchAuction.findByTitle", query = "SELECT b FROM BatchAuction b WHERE b.title = :title")
        , @NamedQuery(name = "BatchAuction.findByMinBid", query = "SELECT b FROM BatchAuction b WHERE b.minBid = :minBid")})
public class BatchAuction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_bid")
    private int minBid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionId")
    private List<BatchProduct> batchProductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batchAuction")
    private List<UserBatchBid> userBatchBidList;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User ownerId;

    public BatchAuction() {
    }

    public BatchAuction(Integer id) {
        this.id = id;
    }

    public BatchAuction(Date deadline, String title, int minBid) {
        this.deadline = deadline;
        this.title = title;
        this.minBid = minBid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinBid() {
        return minBid;
    }

    public void setMinBid(int minBid) {
        this.minBid = minBid;
    }

    @XmlTransient
    public List<BatchProduct> getBatchProductList() {
        return batchProductList;
    }

    public void setBatchProductList(List<BatchProduct> batchProductList) {
        this.batchProductList = batchProductList;
    }

    @XmlTransient
    public List<UserBatchBid> getUserBatchBidList() {
        return userBatchBidList;
    }

    public void setUserBatchBidList(List<UserBatchBid> userBatchBidList) {
        this.userBatchBidList = userBatchBidList;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchAuction)) {
            return false;
        }
        BatchAuction other = (BatchAuction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject jsonObject = jsonObjectBuilder
                .add("id", id)
                .add("ownerUserName", getOwnerId().getUserName())
                .add("deadline", deadline.toString())
                .add("minBid", minBid)
                .add("title", title)
                .build();
        return jsonObject.toString();
    }

}
