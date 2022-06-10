package com.bidder.BidderApp.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BidderNotFoundException extends RuntimeException{
    public BidderNotFoundException(String s) {
        super(s);
    }
}
