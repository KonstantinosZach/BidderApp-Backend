package com.bidder.BidderApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@JsonSerialize
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)   //one category todo add more
    private String category;

    @Column(nullable = false)
    private Float currently;  //current price

    @Column
    private Float buyPrice;

    @Column(nullable = false)
    private Float firstBid;  //first price

    @Column(nullable = false)
    private Integer numberOfBids = 0;

    @OneToMany(mappedBy = "item")
    private Set<Bid> bids;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String country;

    @Column(length = 50)
    private String started = "";

    @Column(length = 50)
    private String ends = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @Column(nullable = false)
    private String description;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {this.seller = seller;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getCurrently() {
        return currently;
    }

    public void setCurrently(Float currently) {
        this.currently = currently;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Float getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(Float firstBid) {
        this.firstBid = firstBid;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public Set<Bid> getBids() { return bids; }

    public void setBids(Set<Bid> bids) { this.bids = bids; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) { this.ends = ends; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", currently=" + currently +
                ", buyPrice=" + buyPrice +
                ", firstBid=" + firstBid +
                ", numberOfBids=" + numberOfBids +
                ", bids=" + bids +
                ", location='" + location + '\'' +
                ", country='" + country + '\'' +
                ", started=" + started +
                ", ends=" + ends +
                ", description='" + description + '\'' +
                '}';
    }
}
