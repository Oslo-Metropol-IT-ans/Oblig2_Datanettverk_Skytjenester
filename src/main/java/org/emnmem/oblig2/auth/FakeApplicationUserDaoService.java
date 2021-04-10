package org.emnmem.oblig2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        applicationUsers.add(
                new ApplicationUser("user",
                        passwordEncoder.encode("password"),
                        null,
                        true, true, true, true));
        applicationUsers.add(new ApplicationUser("trainee", passwordEncoder.encode("password"),
                null, true, true, true, true));
        applicationUsers.add(new ApplicationUser("admin", passwordEncoder.encode("password"),
                null, true, true, true, true));


        return applicationUsers;
    }
}
