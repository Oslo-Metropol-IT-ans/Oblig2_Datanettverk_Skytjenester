package org.emnmem.oblig2.repository;

import org.emnmem.oblig2.model.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser, Integer> {
}

