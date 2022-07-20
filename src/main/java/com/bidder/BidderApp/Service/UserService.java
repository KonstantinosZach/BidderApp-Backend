package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Exception.UserAlreadyExists;
import com.bidder.BidderApp.Exception.UserNotFoundException;
import com.bidder.BidderApp.Repo.UserRepo;
import com.bidder.BidderApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        boolean userExists = userRepo.findUserByUsername(user.getUsername()).isPresent();
        if(userExists)
            throw new UserAlreadyExists("User with username:" + user.getUsername() + "already exists");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public void deleteUser(String username){
        userRepo.delete(findUserByUsername(username));
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username:"+username+"was not found!"));
    }
}
