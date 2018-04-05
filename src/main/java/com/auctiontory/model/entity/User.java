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
 * @author mahrous
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
        , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
        , @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName")
        , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
        , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
        , @NamedQuery(name = "User.findByUserNameAndPassword", query = "SELECT u FROM User u WHERE u.userName = :userName and u.password = :password")
        , @NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user_name")
    private String userName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "email")
    private String email;
    @Column(name = "type")
    private UserType type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBatchBid> userBatchBidList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private List<BatchAuction> batchAuctionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPartBid> userPartBidList;
    @OneToMany(mappedBy = "ownerId")
    private List<PartsAuction> partsAuctionList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String userName, String password, String email, UserType type) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public User(String userName, String password, String email, UserType type) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @XmlTransient
    public List<UserBatchBid> getUserBatchBidList() {
        return userBatchBidList;
    }

    public void setUserBatchBidList(List<UserBatchBid> userBatchBidList) {
        this.userBatchBidList = userBatchBidList;
    }

    @XmlTransient
    public List<BatchAuction> getBatchAuctionList() {
        return batchAuctionList;
    }

    public void setBatchAuctionList(List<BatchAuction> batchAuctionList) {
        this.batchAuctionList = batchAuctionList;
    }

    @XmlTransient
    public List<UserPartBid> getUserPartBidList() {
        return userPartBidList;
    }

    public void setUserPartBidList(List<UserPartBid> userPartBidList) {
        this.userPartBidList = userPartBidList;
    }

    @XmlTransient
    public List<PartsAuction> getPartsAuctionList() {
        return partsAuctionList;
    }

    public void setPartsAuctionList(List<PartsAuction> partsAuctionList) {
        this.partsAuctionList = partsAuctionList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ id=" + id + " ]";
    }

}
