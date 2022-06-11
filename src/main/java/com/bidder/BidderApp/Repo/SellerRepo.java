package com.bidder.BidderApp.Repo;

import com.bidder.BidderApp.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {
    Seller findSellerById(Integer id);
}
