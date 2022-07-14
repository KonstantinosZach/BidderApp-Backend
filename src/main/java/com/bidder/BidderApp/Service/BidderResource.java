package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.Exception.BidderFoundException;
import com.bidder.BidderApp.model.Bid;
import com.bidder.BidderApp.model.Bidder;
import com.bidder.BidderApp.model.Item;
import com.bidder.BidderApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/bidder")
public class BidderResource{
    private final BidderService bidderService;
    private final UserService userService;
    private final ItemService itemService;

    public BidderResource(BidderService bidderService, UserService userService, ItemService itemService){
        this.bidderService = bidderService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Bidder> addBidder(@PathVariable("username") String username, @RequestBody Bidder bidder){
        User user = userService.findUserByUsername(username);

        if(user.getBidder() != null)
            throw new BidderFoundException("User:" + user.getUsername() + " is already a bidder");
        else {
            Bidder newBidder = bidderService.addBidder(bidder);
            user.setBidder(bidder);
            userService.updateUser(user);
            return new ResponseEntity<>(newBidder, HttpStatus.CREATED);
        }
    }

    @DeleteMapping ("/delete/{username}")
    public ResponseEntity<?> deleteBidder(@PathVariable("username") String username){

        //todo if bidder has alive bids cant delete acc

        Integer id = userService.findUserByUsername(username).getBidder().getId();
        bidderService.deleteBidder(id);

        User user = userService.findUserByUsername(username);
        user.setBidder(null);
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Bidder> getBidderByUsername (@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        Bidder bidder = user.getBidder();
        return new ResponseEntity<>(bidder, HttpStatus.OK);
    }

    @GetMapping("/find-items/{username}")
    public List<Item> getItemsWithBid (@PathVariable("username") String username) {
        List<Item> list = itemService.findAllItems();
        List<Item> bidItems = new ArrayList<>();

        for(Item item : list){
            if(!item.getSeller().getUser().getUsername().equals(username)){
                List<Bid> bids = item.getBids();
                for(Bid bid: bids){
                    if(bid.getBidder().getUser().getUsername().equals(username)){
                        bidItems.add(item);
                        break;
                    }
                }
            }
        }
        return bidItems;
    }
}
