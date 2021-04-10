package org.emnmem.oblig2.service;

import org.emnmem.oblig2.model.Room;
import org.emnmem.oblig2.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public int addRoom(Room room) {
        var result = roomRepository.save(room);
        return result.getId();
    }

    public List<Room> getAll() {
        var result = roomRepository.findAll();
        return result;
    }

    public Room getOne(String id) {
        try {
            var result = roomRepository.findById(Integer.parseInt(id));
            return result.orElse(null);
        } catch (Exception e) {
            return null;
        }

    }
}
