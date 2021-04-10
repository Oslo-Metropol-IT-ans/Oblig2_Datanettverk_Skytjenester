package org.emnmem.oblig2.repository;

import org.emnmem.oblig2.dto.UserDto;
import org.emnmem.oblig2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

     User save(UserDto user);
}
