package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Exception.BidderNotFoundException;
import com.bidder.BidderApp.Repo.BidderRepo;
import com.bidder.BidderApp.model.Bidder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidderService {
    private final BidderRepo bidderRepo;

    @Autowired
    public BidderService(BidderRepo bidderRepo) { this.bidderRepo = bidderRepo; }

    public Bidder addBidder(Bidder bidder){
        return  bidderRepo.save(bidder);
    }

    public void deleteBidder(Integer id){
        bidderRepo.delete(findBidderById(id));
    }

    public Bidder updateBidder(Bidder bidder){
        return bidderRepo.save(bidder);
    }

    public Bidder findBidderById(Integer id){
        return bidderRepo.findBidderById(id)
                .orElseThrow(() -> new BidderNotFoundException("Bidder with id:"+ id +"was not found!"));
    }
}