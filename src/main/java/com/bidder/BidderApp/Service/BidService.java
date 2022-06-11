package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Repo.BidRepo;
import com.bidder.BidderApp.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BidService {
    private  final BidRepo bidRepo;

    @Autowired
    public BidService(BidRepo bidRepo) { this.bidRepo = bidRepo; }

    public Bid addBid(Bid bid){ return bidRepo.save(bid); }

    public List<Bid> findAllBids(){
        return bidRepo.findAll();
    }

    public Bid updateBid(Bid bid){
        return bidRepo.save(bid);
    }

    public void deleteBid(Integer id){
        bidRepo.deleteById(id);
    }

}
