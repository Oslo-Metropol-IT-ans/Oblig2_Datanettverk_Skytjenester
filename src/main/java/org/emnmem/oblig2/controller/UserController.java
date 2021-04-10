package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") String id) {
        User result = userService.getById(id);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<User> deleteUser(@PathVariable("user_id") String id) {
        User result = userService.deleteById(id);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
