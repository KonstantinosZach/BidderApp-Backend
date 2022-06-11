package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemResource {
    private final ItemService itemService;

    public ItemResource(ItemService itemService) { this.itemService = itemService; }

    @PostMapping("/add/{username}")
    public ResponseEntity<Item> addItem(@PathVariable("username") String username, @RequestBody Item item){
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) throws Exception {
        //todo delete item only if there is not bid
        Item item = itemService.findItemById(id);
        if(item.getNumberOfBids() > 0)
            throw new Exception("Item has already bid");

        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/")
    public List<Item> getAllItems(){
        return itemService.findAllItems();
    }
}
