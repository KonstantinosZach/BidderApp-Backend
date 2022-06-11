package com.bidder.BidderApp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonSerialize
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer rating;

    @OneToMany(mappedBy = "seller")
    private Set<Item> items;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "seller")
    private User user;

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Seller{" + '\'' +
                "id= " + id + '\'' +
                ", rating=" + rating + '\'' +
                '}';
    }
}
