package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.Repo.ItemRepo;
import com.bidder.BidderApp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ItemService {

    static class itemSort implements Comparator<Item> {
        public int compare(Item a, Item b) {
            return a.getId() - b.getId();
        }
    }

    private  final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) { this.itemRepo = itemRepo; }

    public Item addItem(Item item){ return itemRepo.save(item); }

    public List<Item> findAllItems(){
        List<Item> sorted = itemRepo.findAll();
        sorted.sort(new itemSort());
        return sorted;
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
