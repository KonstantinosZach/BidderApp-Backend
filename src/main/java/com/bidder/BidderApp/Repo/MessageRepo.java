package com.bidder.BidderApp.Repo;
import com.bidder.BidderApp.model.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepo extends JpaRepository<UserMessages, Integer> {
    UserMessages findMessageById(Integer id);
}

