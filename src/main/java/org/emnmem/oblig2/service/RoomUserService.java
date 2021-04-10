package org.emnmem.oblig2.service;

import org.emnmem.oblig2.model.Room;
import org.emnmem.oblig2.model.RoomUser;
import org.emnmem.oblig2.model.RoomUserId;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.repository.RoomRepository;
import org.emnmem.oblig2.repository.RoomUserRepository;
import org.emnmem.oblig2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomUserService {

    Logger logger = LoggerFactory.getLogger(RoomUserService.class);

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomUserRepository roomUserRepository;

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(String id) {
        List<User> userList = new ArrayList<>();
        var result = roomUserRepository.findAll();
        for (RoomUser user : result) {
            if (String.valueOf(user.getRoomUserId().getRoom_id()).equals(id)) {
                try {
                    Optional<User> add = userRepository.findById(Integer.parseInt(id));
                    add.ifPresent(userList::add);
                } catch (Exception ignored) {}

            }
        }
        return userList;
    }

    public boolean addOne(String id, String userId) {
        try {
            Optional<User> user = userRepository.findById(Integer.parseInt(userId));
            Optional<Room> room = roomRepository.findById(Integer.parseInt(id));
            if (user.isPresent() && room.isPresent()) {
                roomUserRepository.save(new RoomUser(new RoomUserId(room.get().getId(), user.get().getId())));
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
