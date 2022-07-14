package com.bidder.BidderApp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonSerialize
public class UserMessages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String date;

    @Column
    private Boolean read = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sender_id", referencedColumnName="username")
    private User sender;
    @Column
    private Boolean deletedBySender = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "receiver_id", referencedColumnName="username")
    private User receiver;
    @Column
    private Boolean deletedByReceiver = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getRead() {return read;}

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getDeletedBySender() {return deletedBySender;}

    public void setDeletedBySender(Boolean deletedBySender) {this.deletedBySender = deletedBySender;}

    public Boolean getDeletedByReceiver() {return deletedByReceiver;}

    public void setDeletedByReceiver(Boolean deletedByReceiver) {this.deletedByReceiver = deletedByReceiver;}

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
