package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername (@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/accept/{username}")
    public ResponseEntity<User> acceptUser(@PathVariable("username") String username){
        User toBeAccepted = userService.findUserByUsername(username);
        toBeAccepted.setAccepted(true);

        User updatedUser = userService.updateUser(toBeAccepted);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user){
        User toBeUpdated = userService.findUserByUsername(username);

        toBeUpdated.setFirstname(user.getFirstname());
        toBeUpdated.setLastname(user.getLastname());
        toBeUpdated.setAddress(user.getAddress());
        toBeUpdated.setEmail(user.getEmail());
        toBeUpdated.setPhone(user.getPhone());
        toBeUpdated.setAfm(user.getAfm());
        toBeUpdated.setImageUrl(user.getImageUrl());
        toBeUpdated.setPassword(user.getPassword());
        toBeUpdated.setPostNumber(user.getPostNumber());
        toBeUpdated.setAccepted(user.getAccepted());

        User updatedUser = userService.updateUser(toBeUpdated);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
