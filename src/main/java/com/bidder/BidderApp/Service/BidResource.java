package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.model.Bid;
import com.bidder.BidderApp.model.Bidder;
import com.bidder.BidderApp.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Set<Bid> itemBids = item.getBids();
        itemBids.add(newBid);
        item.setNumberOfBids(item.getNumberOfBids() + 1);
        itemService.updateItem(item);

        return new ResponseEntity<>(newBid, HttpStatus.CREATED);
    }

    @GetMapping("/find/{username}")
    public Set<Bid> getAllBids (@PathVariable("username") String username) {
        Bidder bidder = userService.findUserByUsername(username).getBidder();
        return bidder.getBids();
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteBid(@PathVariable("id") Integer id){
//        bidService.deleteBid(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
