package com.bidder.BidderApp.Repo;

import com.bidder.BidderApp.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer> {
}
