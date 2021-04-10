package org.emnmem.oblig2.repository;

import org.emnmem.oblig2.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer> {
}
