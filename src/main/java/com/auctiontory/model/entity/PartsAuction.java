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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mahrous
 */
@Entity
@Table(name = "parts_auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartsAuction.findAll", query = "SELECT p FROM PartsAuction p")
    , @NamedQuery(name = "PartsAuction.findById", query = "SELECT p FROM PartsAuction p WHERE p.id = :id")
    , @NamedQuery(name = "PartsAuction.findByDeadline", query = "SELECT p FROM PartsAuction p WHERE p.deadline = :deadline")
    , @NamedQuery(name = "PartsAuction.findByTitle", query = "SELECT p FROM PartsAuction p WHERE p.title = :title")})
public class PartsAuction implements Serializable {

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
    @OneToMany(mappedBy = "auctionId" ,cascade=CascadeType.ALL)
    private List<PartsProduct> partsProductList;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;



    @Transient
    private boolean active;

    @Transient
    private ArrayList<User> bidders;

    public PartsAuction() {
    }

    public PartsAuction(Integer id) {
        this.id = id;
    }

    public PartsAuction(Integer id, Date deadline, String title) {
        this.id = id;
        this.deadline = deadline;
        this.title = title;
    }

    public PartsAuction(PartsAuction partsAuction) {
        this.id = partsAuction.id;
        this.title = partsAuction.title;
        this.deadline = partsAuction.deadline;
        this.active = partsAuction.active;
        this.ownerId = new User();
        this.ownerId.setUserName(partsAuction.getOwnerId().getUserName());

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

    @XmlTransient
    public List<PartsProduct> getPartsProductList() {
        return partsProductList;
    }

    public void setPartsProductList(List<PartsProduct> partsProductList) {
        this.partsProductList = partsProductList;
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
        if (!(object instanceof PartsAuction)) {
            return false;
        }
        PartsAuction other = (PartsAuction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mahrous.model.entity.PartsAuction[ id=" + id + " ]";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<User> getBidders() {
        return bidders;
    }

    public void setBidders(ArrayList<User> bidders) {
        this.bidders = bidders;
    }


}
