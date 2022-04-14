package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Exception.UserNotFoundException;
import com.bidder.BidderApp.Repo.UserRepo;
import com.bidder.BidderApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user){
        //user.setUsername(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public void deleteUser(String username){
        userRepo.deleteUserByUsername(username);

    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username:"+username+"was not found!"));
    }
}
