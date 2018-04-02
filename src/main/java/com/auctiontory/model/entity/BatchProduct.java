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
import java.io.Serializable;

/**
 * @author mahrous
 */
@Entity
@Table(name = "batch_product")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "BatchProduct.findAll", query = "SELECT b FROM BatchProduct b")
        , @NamedQuery(name = "BatchProduct.findById", query = "SELECT b FROM BatchProduct b WHERE b.id = :id")
        , @NamedQuery(name = "BatchProduct.findByName", query = "SELECT b FROM BatchProduct b WHERE b.name = :name")
        , @NamedQuery(name = "BatchProduct.findByDescription", query = "SELECT b FROM BatchProduct b WHERE b.description = :description")})
public class BatchProduct implements Serializable {

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
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BatchAuction auctionId;

    public BatchProduct() {
    }

    public BatchProduct(Integer id) {
        this.id = id;
    }

    public BatchProduct(String name, BatchAuction auctionId) {
        this.auctionId = auctionId;
        this.name = name;
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

    public BatchAuction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(BatchAuction auctionId) {
        this.auctionId = auctionId;
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
        if (!(object instanceof BatchProduct)) {
            return false;
        }
        BatchProduct other = (BatchProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.BatchProduct[ id=" + id + " ]";
    }

}
