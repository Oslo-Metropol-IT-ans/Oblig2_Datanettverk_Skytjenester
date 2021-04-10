package org.emnmem.oblig2.controller;

import org.emnmem.oblig2.dto.UserDto;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.repository.UserRepository;
import org.emnmem.oblig2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class UsersController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UserService userService;

    @PostMapping("/addOne")
    public ResponseEntity<User> addUser(@RequestBody UserDto user) {
        logger.info(user.toString());
        User userResponse = userService.addOne(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
