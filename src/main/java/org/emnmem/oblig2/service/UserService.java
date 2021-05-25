package org.emnmem.oblig2.service;

import org.emnmem.oblig2.dto.UserDto;
import org.emnmem.oblig2.model.User;
import org.emnmem.oblig2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addOne(UserDto user) {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(15));
        User setUser = new User();
        setUser.setUsername(user.getUsername());
        setUser.setPassword(password);

        return userRepository.save(setUser);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        try {
            Optional<User> user = userRepository.findById(Integer.parseInt(id));
            return user.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public User deleteById(String id) {
        User result = getById(id);
        userRepository.deleteById(Integer.parseInt(id));
        return result;
    }
}
