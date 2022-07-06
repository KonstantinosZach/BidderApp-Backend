package com.bidder.BidderApp.Service;

import com.bidder.BidderApp.model.Item;
import com.bidder.BidderApp.model.Seller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/item")
public class ItemResource {
    private final ItemService itemService;
    private final UserService userService;
    private final SellerService sellerService;

    public ItemResource(ItemService itemService, UserService userService, SellerService sellerService) {
        this.itemService = itemService;
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Item> addItem(@PathVariable("username") String username, @RequestBody Item item){
        Seller seller = userService.findUserByUsername(username).getSeller();
        Item newItem = itemService.addItem(item);
        newItem.setSeller(seller);

        Set<Item> items = seller.getItems();
        items.add(newItem);
        seller.setItems(items);
        sellerService.updateSeller(seller);

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

    @GetMapping("/find/{username}")
    public List<Item> getSellerItems(@PathVariable("username") String username){
        List<Item> list = itemService.findAllItems();
        List<Item> sellerList = new ArrayList<>();

        for(Item item : list)
            if(item.getSeller().getUser().getUsername().equals(username))
                sellerList.add(item);

        return sellerList;
    }
}
