package com.bidder.BidderApp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonSerialize
public class AuthRequest implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
