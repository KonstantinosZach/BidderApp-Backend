package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.model.Bid;
import com.bidder.BidderApp.model.Bidder;
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

    public BidResource(BidService bidService, UserService userService, BidderService bidderService) {
        this.bidService = bidService;
        this.userService = userService;
        this.bidderService = bidderService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Bid> addBid(@PathVariable("username") String username, @RequestBody Bid bid){
        Bidder bidder = userService.findUserByUsername(username).getBidder();

        Bid newBid = bidService.addBid(bid);
        newBid.setBidder(bidder);

        Set<Bid> bids = bidder.getBids();
        bids.add(newBid);
        bidder.setBids(bids);
        bidderService.updateBidder(bidder);

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
