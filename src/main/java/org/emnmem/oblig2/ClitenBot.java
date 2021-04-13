package org.emnmem.oblig2;

import org.emnmem.oblig2.controller.MessageController;
import org.emnmem.oblig2.controller.RoomsController;
import org.emnmem.oblig2.controller.UserController;
import org.emnmem.oblig2.controller.UsersController;
import org.emnmem.oblig2.dto.UserDto;
import org.emnmem.oblig2.model.Message;
import org.emnmem.oblig2.model.Room;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.service.MessageService;
import org.emnmem.oblig2.service.RoomService;
import org.emnmem.oblig2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClitenBot {
    UserDto user = new UserDto("Erik", "password");
    UserDto emilie = new UserDto("Emilie", "password");
    UserDto nicolai = new UserDto("Nicolai", "password");

    List<User> userList = new ArrayList<>();
    int roomId = 0;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    MessageService messageService;

    @PostConstruct
    public void init() {
        register();
    }

    void register() {
        userList.add(userService.addOne(user));
        userList.add(userService.addOne(emilie));
        userList.add(userService.addOne(nicolai));
        System.out.println(userList.size());
        roomId = roomService.addRoom(new Room(0, "Eriks fantastiske rom"));
    }

    public void svar(Message msg) {
        if (msg.getRoom_id() == roomId) {
            messageService.sendMessageBot(new Message(0, roomId, userList.get(0).getId(), "Hei på deg"));
            messageService.sendMessageBot(new Message(0, roomId, userList.get(1).getId(), "Hello"));
            messageService.sendMessageBot(new Message(0, roomId, userList.get(2).getId(), "Helå"));
        }
    }
}
