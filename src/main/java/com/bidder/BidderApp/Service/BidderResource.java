package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.Exception.BidderFoundException;
import com.bidder.BidderApp.model.Bidder;
import com.bidder.BidderApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bidder")
public class BidderResource{
    private final BidderService bidderService;
    private final UserService userService;

    public BidderResource(BidderService bidderService, UserService userService){
        this.bidderService = bidderService;
        this.userService = userService;
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
}
