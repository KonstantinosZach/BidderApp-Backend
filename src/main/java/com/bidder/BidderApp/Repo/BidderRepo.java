package com.bidder.BidderApp.Repo;
import com.bidder.BidderApp.model.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BidderRepo extends JpaRepository<Bidder, Integer> {
    Optional<Bidder> findBidderById(Integer id);
}
