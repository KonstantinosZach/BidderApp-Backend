package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.model.User;
import com.bidder.BidderApp.model.UserMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/message")
public class MessageResource {
    private final MessageService messageService;
    private final UserService userService;

    public MessageResource(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/add/{sender}/{receiver}")
    public ResponseEntity<UserMessages> sendMessage(@PathVariable("sender") String senderUsername, @PathVariable("receiver") String receiverUsername, @RequestBody UserMessages userMessages){
        User sender = userService.findUserByUsername(senderUsername);
        User receiver = userService.findUserByUsername(receiverUsername);

        userMessages.setSender(sender);
        userMessages.setReceiver(receiver);

        List<UserMessages> receivedMessages = receiver.getReceived();
        receivedMessages.add(userMessages);
        receiver.setReceived(receivedMessages);

        List<UserMessages> sentMessages = sender.getSent();
        sentMessages.add(userMessages);
        sender.setSent(sentMessages);

        messageService.sendMessage(userMessages);

        return new ResponseEntity<>(userMessages, HttpStatus.CREATED);
    }

    @DeleteMapping ("/delete-received/{id}")
    public ResponseEntity<?> deleteReceived(@PathVariable("id") Integer id){
        UserMessages message = messageService.findMessage(id);
        if(message != null) {
            message.setDeletedByReceiver(true);
            messageService.updateMessage(message);

            if(message.getDeletedBySender())
                messageService.deleteMessage(message);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/delete-sent/{id}")
    public ResponseEntity<?> deleteSent(@PathVariable("id") Integer id){
        UserMessages message = messageService.findMessage(id);
        if(message != null) {
            message.setDeletedBySender(true);
            messageService.updateMessage(message);

            if(message.getDeletedByReceiver())
                messageService.deleteMessage(message);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-received/{username}")
    public List<UserMessages> getAllReceivedMessages(@PathVariable("username") String username){
        List<UserMessages> messages = userService.findUserByUsername(username).getReceived();
        List<UserMessages> onlineMessages = new ArrayList<>();
        for(UserMessages mess: messages){
            if(!mess.getDeletedByReceiver())
                onlineMessages.add(mess);
        }
        Collections.reverse(onlineMessages);
        return onlineMessages;
    }

    @GetMapping("/find-sender/{id}")
    public User getSenderByMessageId(@PathVariable("id") Integer id){
        return messageService.findMessage(id).getSender();
    }

    @GetMapping("/find-receiver/{id}")
    public User getReceiverByMessageId(@PathVariable("id") Integer id){
        return messageService.findMessage(id).getReceiver();
    }

    @GetMapping("/find-message/{id}")
    public UserMessages getMessageById(@PathVariable("id") Integer id){
        return messageService.findMessage(id);
    }

    @PutMapping("/read-message/{id}")
    public ResponseEntity<?> messageIsRead(@PathVariable("id") Integer id){
        UserMessages message = messageService.findMessage(id);
        message.setRead(true);
        messageService.updateMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-sent/{username}")
    public List<UserMessages> getAllSentMessages(@PathVariable("username") String username){
        List<UserMessages> messages = userService.findUserByUsername(username).getSent();
        List<UserMessages> onlineMessages = new ArrayList<>();
        for(UserMessages mess: messages){
            if(!mess.getDeletedBySender())
                onlineMessages.add(mess);
        }
        Collections.reverse(onlineMessages);
        return onlineMessages;
    }
}