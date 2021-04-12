package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.dto.RoomUserDto;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.service.RoomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/room/{room_id}/users")
public class RoomUserController {

    Logger logger = LoggerFactory.getLogger(RoomUserController.class);

    @Autowired
    RoomUserService roomUserService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@PathVariable("room_id") String id){
        var response = roomUserService.getAll(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@PathVariable("room_id") String id, @RequestBody RoomUserDto userId) {
        logger.info("id: " + id + " user_id: " + userId.getUserId());
        return roomUserService.addOne(id, userId.getUserId()) ?
                new ResponseEntity<>("User added to room", HttpStatus.OK) :
                new ResponseEntity<>("User or room not found", HttpStatus.BAD_REQUEST);
    }
}
