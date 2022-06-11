package com.bidder.BidderApp.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonSerialize
public class Bidder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer rating;

    @Column
    private String country;

    @Column
    private String location;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bidder")
    private User user;

    @OneToMany(mappedBy = "bidder")
    private Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Bidder{" + '\'' +
                "id= " + id + '\'' +
                ", rating=" + rating + '\'' +
                ", country='" + country + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
