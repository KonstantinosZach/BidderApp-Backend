package com.bidder.BidderApp.Exception;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class BidderFoundException extends RuntimeException{
    public BidderFoundException(String s) {
        super(s);
    }
}