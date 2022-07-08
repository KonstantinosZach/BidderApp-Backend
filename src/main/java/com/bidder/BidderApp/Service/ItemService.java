package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Repo.ItemRepo;
import com.bidder.BidderApp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private  final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) { this.itemRepo = itemRepo; }

    public Item addItem(Item item){ return itemRepo.save(item); }

    public List<Item> findAllItems(){
        return itemRepo.findAll();
    }

    public Item findItemById(Integer id){
        return itemRepo.findItemById(id);
    }
    public Item updateItem(Item item){
        return itemRepo.save(item);
    }

    public void deleteItem(Integer id){
        itemRepo.deleteById(id);
    }
}
