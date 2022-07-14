package com.bidder.BidderApp.Service;
import com.bidder.BidderApp.Repo.MessageRepo;
import com.bidder.BidderApp.model.UserMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public UserMessages sendMessage(UserMessages message){
       return messageRepo.save(message);
    }

    public UserMessages updateMessage(UserMessages message){
        return messageRepo.save(message);
    }

    public void deleteMessage(UserMessages messages){
         messageRepo.delete(messages);
    }

    public UserMessages findMessage(Integer id){
        return messageRepo.findMessageById(id);
    }

}
