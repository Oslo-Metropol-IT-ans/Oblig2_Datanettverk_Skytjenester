package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.model.Room;
import org.emnmem.oblig2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room/{room_id}")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping
    public ResponseEntity<Room> getRoom(@PathVariable("room_id") String id) {
        Room room = roomService.getOne(id);
        return room != null ?
                new ResponseEntity<>(room, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
