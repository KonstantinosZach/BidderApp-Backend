package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.model.Bid;
import com.bidder.BidderApp.model.Bidder;
import com.bidder.BidderApp.model.Item;
import com.bidder.BidderApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bid")
public class BidResource {

    private final BidService bidService;
    private final UserService userService;
    private final BidderService bidderService;
    private final ItemService itemService;

    public BidResource(BidService bidService, UserService userService, BidderService bidderService, ItemService itemService) {
        this.bidService = bidService;
        this.userService = userService;
        this.bidderService = bidderService;
        this.itemService = itemService;
    }

    @PostMapping("/add/{username}/{id}")
    public ResponseEntity<Bid> addBid(@PathVariable("username") String username, @PathVariable("id") Integer id, @RequestBody Bid bid){
        Bidder bidder = userService.findUserByUsername(username).getBidder();
        Item item = itemService.findItemById(id);

        Bid newBid = bidService.addBid(bid);
        newBid.setBidder(bidder);
        newBid.setItem(item);

        Set<Bid> bids = bidder.getBids();
        bids.add(newBid);
        bidder.setBids(bids);
        bidderService.updateBidder(bidder);

        List<Bid> itemBids = item.getBids();
        itemBids.add(newBid);
        itemService.updateItem(item);

        return new ResponseEntity<>(newBid, HttpStatus.CREATED);
    }

    @GetMapping("/find/{username}")
    public Set<Bid> getAllBids (@PathVariable("username") String username) {
        Bidder bidder = userService.findUserByUsername(username).getBidder();
        return bidder.getBids();
    }

    @GetMapping("/find/item-bids/{id}")
    public List<Bid> getAllItemBids (@PathVariable("id") Integer id) {
        Item item = itemService.findItemById(id);
        Collections.reverse(item.getBids());
        return item.getBids();
    }

    @GetMapping("/find-user/{id}")
    public User getUserByBidId (@PathVariable("id") Integer id) {
        Bid bid = bidService.findBidById(id);
        return bid.getBidder().getUser();
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteBid(@PathVariable("id") Integer id){
//        bidService.deleteBid(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
