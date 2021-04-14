package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.dto.MessageDto;
import org.emnmem.oblig2.model.Message;
import org.emnmem.oblig2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/en/{roomId}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable("roomId") int roomId){
        System.out.println(roomId);
        List<Message> list = messageService.messages(roomId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/fire/{roomId}")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable("roomId") int roomId){
        List<MessageDto> list = messageService.messageDtosList(roomId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/to")
    public ResponseEntity<List<Message>> getMessageRoomAndUser (int roomId, int userId){
        List<Message> list = messageService.messagesByUserAndRoom(roomId, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> getSendMessage (@RequestBody Message message){
        Message theMessage = messageService.sendMessage(message);
        return new ResponseEntity<>(theMessage, HttpStatus.OK);
    }

    @PostMapping("/update/{roomId}")
    public ResponseEntity<List<MessageDto>> updateMessage (@RequestBody List<Message> listIn,
                                                           @PathVariable("roomId") Integer roomId) throws InterruptedException {
        return new ResponseEntity<>(messageService.messageDtosUpdate(listIn, roomId), HttpStatus.OK);
    }

}
