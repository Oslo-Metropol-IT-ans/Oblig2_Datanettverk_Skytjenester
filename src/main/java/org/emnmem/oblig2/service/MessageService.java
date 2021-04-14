package org.emnmem.oblig2.service;

import org.emnmem.oblig2.ClientBot;
import org.emnmem.oblig2.dto.MessageDto;
import org.emnmem.oblig2.model.Message;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.repository.MessageRepository;
import org.emnmem.oblig2.repository.OtherRepository;
import org.emnmem.oblig2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OtherRepository otherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientBot clitenBot;

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

    public List<MessageDto> messageDtosList(int roomId) {
        List<Message> list = messages(roomId);
        List<MessageDto> returnList = new ArrayList<>();
        for (Message message : list) {
            User user = userRepository.getOne(message.getUser_id());
            returnList.add(new MessageDto(user.getUsername(), message.getId(),
                    message.getRoom_id(), message.getUser_id(), message.getMessage()));
        }

        return returnList;
    }

    public List<MessageDto> messageDtosUpdate(List<Message> listIn, int roomId) throws InterruptedException {
        System.out.println(Thread.currentThread().getId());
        while (listIn.equals(messages(roomId))) {
            Thread.sleep(1000);
        }
        System.out.println("change");
        return messageDtosList(roomId);
    }

    public Message sendMessage(Message message){
        var response = messageRepository.save(message);
        /*Server.socketIntegerMap.forEach((socket, integer) -> {
            System.out.println(socket);
            if (integer == message.getRoom_id()) {
                try {
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    System.out.println(socket + ":" + true);
                    pw.println(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
         */

        clitenBot.svar(message);
        return response;
    }

    public void sendMessageBot(Message message) {
        if (message.getMessage() != null)
            messageRepository.save(message);
    }
}
