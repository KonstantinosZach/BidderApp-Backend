package com.bidder.BidderApp.Repo;

import com.bidder.BidderApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    Item findItemById(Integer id);
}
