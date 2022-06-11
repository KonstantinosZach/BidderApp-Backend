package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Exception.SellerFoundException;
import com.bidder.BidderApp.model.Seller;
import com.bidder.BidderApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerResource {
    private final SellerService sellerService;
    private final UserService userService;

    public SellerResource(SellerService sellerService, UserService userService){
        this.sellerService = sellerService;
        this.userService = userService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Seller> addSeller(@PathVariable("username") String username, @RequestBody Seller seller){
        User user = userService.findUserByUsername(username);

        if(user.getSeller() != null)
            throw new SellerFoundException("User:" + user.getUsername() + " is already a bidder");
        else {
            Seller newSeller = sellerService.addSeller(seller);
            user.setSeller(seller);
            userService.updateUser(user);
            return new ResponseEntity<>(newSeller, HttpStatus.CREATED);
        }
    }

    @DeleteMapping ("/delete/{username}")
    public ResponseEntity<?> deleteSeller(@PathVariable("username") String username){

        //todo if seller has alive items cant delete acc

        Integer id = userService.findUserByUsername(username).getSeller().getId();
        sellerService.deleteSeller(id);

        User user = userService.findUserByUsername(username);
        user.setBidder(null);
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Seller> getSellerByUsername (@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        Seller seller = user.getSeller();
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

}
