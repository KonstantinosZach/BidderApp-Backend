package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Repo.SellerRepo;
import com.bidder.BidderApp.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private final SellerRepo sellerRepo;

    @Autowired
    public SellerService(SellerRepo sellerRepo){ this.sellerRepo = sellerRepo; }

    public Seller addSeller(Seller seller){ return sellerRepo.save(seller); }

    public void deleteSeller(Integer id){ sellerRepo.delete(findSellerById(id));}

    public Seller updateSeller(Seller seller){ return sellerRepo.save(seller); }

    public Seller findSellerById(Integer id){
        return sellerRepo.findSellerById(id);
    }
}
