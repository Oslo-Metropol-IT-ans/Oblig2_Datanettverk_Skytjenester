package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.model.Room;
import org.emnmem.oblig2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    RoomService roomService;

    @PostMapping("/addOne")
    public ResponseEntity<Integer> addOneRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.addRoom(room), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }
}
