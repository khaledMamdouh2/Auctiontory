/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import com.auctiontory.view.bean.converters.DateConverter;

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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User ownerId;

    @Transient
    private Integer highestBid;

    @Transient
    private User highestBidderId;

    @Transient
    private Integer numberOfBids;

    @Transient
    private boolean active;

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

    public Integer getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Integer highestBid) {
        this.highestBid = highestBid;
    }

    public User getHighestBidderId() {
        return highestBidderId;
    }

    public void setHighestBidderId(User highestBidderId) {
        this.highestBidderId = highestBidderId;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Copy Constructor to use with JSON
    public BatchAuction(BatchAuction batchAuction) {
        this.id = batchAuction.id;
        this.title = batchAuction.title;
        this.deadline = batchAuction.deadline;
        this.minBid = batchAuction.minBid;
        this.highestBid = batchAuction.highestBid;
        this.active = batchAuction.active;
        this.ownerId = new User();
        this.ownerId.setUserName(batchAuction.getOwnerId().getUserName());
        if (batchAuction.getHighestBidderId() != null) {
            this.highestBidderId = new User();
            this.highestBidderId.setId(batchAuction.getHighestBidderId().getId());
            this.highestBidderId.setUserName(batchAuction.getHighestBidderId().getUserName());
        }
        this.setNumberOfBids(batchAuction.getNumberOfBids());

    }

}
