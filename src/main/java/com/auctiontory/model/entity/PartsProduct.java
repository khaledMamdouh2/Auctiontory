/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auctiontory.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mahrous
 */
@Entity
@Table(name = "parts_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartsProduct.findAll", query = "SELECT p FROM PartsProduct p")
    , @NamedQuery(name = "PartsProduct.findById", query = "SELECT p FROM PartsProduct p WHERE p.id = :id")
    , @NamedQuery(name = "PartsProduct.findByName", query = "SELECT p FROM PartsProduct p WHERE p.name = :name")
    , @NamedQuery(name = "PartsProduct.findByDescription", query = "SELECT p FROM PartsProduct p WHERE p.description = :description")
    , @NamedQuery(name = "PartsProduct.findByMinBid", query = "SELECT p FROM PartsProduct p WHERE p.minBid = :minBid")})
public class PartsProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Size(max = 80)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_bid")
    private int minBid;
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    @ManyToOne
    private PartsAuction auctionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partsProduct")
    private List<UserPartBid> userPartBidList;

    public PartsProduct() {
    }

    public PartsProduct(Integer id) {
        this.id = id;
    }

    public PartsProduct(Integer id, String name, int minBid) {
        this.id = id;
        this.name = name;
        this.minBid = minBid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinBid() {
        return minBid;
    }

    public void setMinBid(int minBid) {
        this.minBid = minBid;
    }

    public PartsAuction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(PartsAuction auctionId) {
        this.auctionId = auctionId;
    }

    @XmlTransient
    public List<UserPartBid> getUserPartBidList() {
        return userPartBidList;
    }

    public void setUserPartBidList(List<UserPartBid> userPartBidList) {
        this.userPartBidList = userPartBidList;
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
        if (!(object instanceof PartsProduct)) {
            return false;
        }
        PartsProduct other = (PartsProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.PartsProduct[ id=" + id + " ]";
    }
    
}
