package org.emnmem.oblig2.auth;

import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("H2")
public class H2ApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    UserRepository userRepository;


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getUsers().stream().filter(
                applicationUser -> username.equals(applicationUser.getUsername())
        ).findFirst();
    }

    List<ApplicationUser> getUsers() {
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            applicationUsers.add(new ApplicationUser(user.getUsername(), user.getPassword(),
                    null, true, true, true,true));
        }
        return applicationUsers;
    }
}
