package com.bidder.BidderApp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class AuthResponse implements Serializable {
    private String username;
    private String accessToken;

    public AuthResponse() { }

    public AuthResponse(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getAccessToken() {return accessToken;}

    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}
}