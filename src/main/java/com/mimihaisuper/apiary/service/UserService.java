package com.mimihaisuper.apiary.service;

import com.mimihaisuper.apiary.model.authModel.User;
import com.mimihaisuper.apiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Integer getModuleCount(String username) {
        User user = userRepository.findByUsername(username).get();
        return user.getAcquisitionModules().size();
    }

    public void deleteUser(String username) {
        userRepository.delete(userRepository.findByUsername(username).get());
    }
}
