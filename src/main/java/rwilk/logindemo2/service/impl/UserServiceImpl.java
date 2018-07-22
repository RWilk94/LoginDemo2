package rwilk.logindemo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.repository.UserRepository;
import rwilk.logindemo2.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        Long userId = this.getUserByUsername(user.getUsername()).getUserId();
        user.setUserId(userId);
        return userRepository.save(user);
    }
}
