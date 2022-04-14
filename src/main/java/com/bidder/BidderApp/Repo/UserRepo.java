package com.bidder.BidderApp.Repo;

import com.bidder.BidderApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    void deleteUserByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
