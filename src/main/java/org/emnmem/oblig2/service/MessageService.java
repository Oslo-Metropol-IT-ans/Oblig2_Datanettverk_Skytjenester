package org.emnmem.oblig2.service;

import org.emnmem.oblig2.model.Message;
import org.emnmem.oblig2.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> messages(int roomId){
        return messageRepository.findAll()
                .stream()
                .filter(message -> message.getRoom_id() == roomId)
                .collect(Collectors.toList());
    }

    public List<Message> messagesByUserAndRoom(int userId, int roomId){
        return messageRepository.findAll()
                .stream()
                .filter(message -> message.getUser_id() == userId && message.getRoom_id() == roomId)
                .collect(Collectors.toList());
    }

    public Message sendMessage(Message message){
        return messageRepository.save(message);
    }
}
