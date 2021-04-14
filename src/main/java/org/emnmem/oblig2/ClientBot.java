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
public class ClientBot {
    UserDto erik = new UserDto("Erik", "password");
    UserDto emilie = new UserDto("Emilie", "password");
    UserDto nicolai = new UserDto("Nicolai", "password");
    UserDto ponyCopter = new UserDto("PonyCopter", "Ponies!");
    UserDto admin = new UserDto("admin", "admin");

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
        userList.add(userService.addOne(erik));
        userList.add(userService.addOne(emilie));
        userList.add(userService.addOne(nicolai));
        userList.add(userService.addOne(ponyCopter));
        userService.addOne(admin);
        System.out.println(userList.size());
        roomId = roomService.addRoom(new Room(0, "Eriks fantastiske rom"));
    }

    String[] analyzed;

    public void svar(Message msg) {
        if (msg.getRoom_id() == roomId) {
            analyzed = WordAnalysing.findAction(msg.getMessage());
            messageService.sendMessageBot(new Message(0, roomId, userList.get(0).getId(), genereteReplyE()));
            messageService.sendMessageBot(new Message(0, roomId, userList.get(1).getId(), genereteReplyEM()));
            messageService.sendMessageBot(new Message(0, roomId, userList.get(2).getId(), genereteReplyN()));
            messageService.sendMessageBot(new Message(0, roomId, userList.get(3).getId(), genereteReplyP()));
        }
    }

    private String genereteReplyE(){
        if (analyzed == null || analyzed[0] == null){
            return null;
        }
        if (analyzed[0].equals("greeting")){
            if (analyzed[1].equals("hi")){
                return "Hello there!";
            }
            else {
                return "nm";
            }
        }
        if (analyzed.length == 2){
            return "I would love to " + analyzed[0] + " " + analyzed[1] + ".";
        }
        else {
            if (analyzed[4].equals("and")){
                return analyzed[2] + "ing " + analyzed[3] + " would be chill";
            }
            else {
                return "I'd rather " + analyzed[2] + ".";
            }
        }
    }
    private String genereteReplyN(){
        if (analyzed == null || analyzed[0] == null){
            return null;
        }
        if (analyzed[0].equals("greeting")){
            if (analyzed[1].equals("hi")){
                return "hej hej";
            }
            else {
                return "nm tbh";
            }
        }
        if (analyzed.length == 2){
            return analyzed[0] + "ing " + analyzed[1] + " sounds nice.";
        }
        else {
            if (analyzed[4].equals("and")){
                return "Yes! Let's " + analyzed[0] + " " + analyzed[1] + " and " + analyzed[2] + " " + analyzed[3];
            }
            else {
                return "I'd rather " + analyzed[0] + ".";
            }
        }
    }
    private String genereteReplyEM(){
        if (analyzed == null || analyzed[0] == null){
            return null;
        }
        if (analyzed[0].equals("greeting")){
            if (analyzed[1].equals("hi")){
                return "helluuuu";
            }
            else {
                return "programming";
            }
        }
        if (analyzed.length == 2){
            return "Nah, I can't! I'm 'boutta meet my in laws \uD83D\uDE43 \uD83D\uDD2B ";
        }
        else {
            if (analyzed[4].equals("and")){
                return "pardon " + analyzed[2] + "ing, quoi?";
            }
            else {
                return "I'd rather " + analyzed[2] + " with some fromage français";
            }
        }
    }
    private String genereteReplyP(){
        if (analyzed == null || analyzed[0] == null){
            return null;
        }
        if (analyzed[0].equals("greeting")){
            if (analyzed[1].equals("hi")){
                return "Hi from the sky!";
            }
            else {
                return "flying around";
            }
        }
        else {
            int oneOrTwo = (int)Math.floor(Math.random()*2);
            return oneOrTwo == 0 ? "I'll do anything as long as I can keep my helicopter *chuff chuff chuff* " +
                    "\uD83D\uDE81\uD83D\uDE81":
                    "When I grow up I wanna be a PEGASUS! *neeeeeeigh* \uD83E\uDD84\uD83E\uDD84";
        }
    }

}
