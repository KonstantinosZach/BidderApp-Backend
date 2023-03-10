package com.bidder.BidderApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@JsonSerialize
public class User implements Serializable, UserDetails {
    @Id
    @Column(nullable = false, unique = true, length = 30)
    private String username; //the assignment must take place before a new entity is passed to EntityManager

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 30)
    private String firstname;

    @Column(nullable = false, length = 30)
    private String lastname;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 30)
    private String address;

    @Column(nullable = false, length = 30)
    private String postNumber;

    @Column(nullable = false, length = 30)
    private String afm;

    @Column(nullable = false)
    private Boolean admin = false;

    @Column(nullable = false)
    private Boolean accepted = false;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bidder_id")
    @JsonIgnore
    private Bidder bidder = null;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller = null;

    @OneToMany(mappedBy = "sender")
    private List<UserMessages> sent;

    @OneToMany(mappedBy = "receiver")
    private List<UserMessages> received;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return null;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}

    public List<UserMessages> getSent() {return sent;}

    public void setSent(List<UserMessages> sent) {this.sent = sent;}

    public List<UserMessages> getReceived() {return received;}

    public void setReceived(List<UserMessages> received) {this.received = received;}

    public Seller getSeller() {return seller;}

    public void setSeller(Seller seller) {this.seller = seller;}

    public Bidder getBidder() { return bidder; }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public void setAdmin(Boolean status) {
        this.admin = status;
    }

    public void setAccepted(Boolean status) {
        this.accepted = status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public String getAfm() {
        return afm;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Boolean getAccepted() {
        return accepted;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", address='" + address + '\'' +
//                ", postNumber='" + postNumber + '\'' +
//                ", afm='" + afm + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", admin=" + admin + '\'' +
//                ", accepted=" + accepted + '\'' +
//                '}';
//    }
}
