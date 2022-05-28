package com.lab11.compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(String name) {
        User userToAdd = new User();
        userToAdd.setName(name);
        userRepository.save(userToAdd);
    }

    @Transactional
    public void modifyUser(String oldName, String newName) {
        userRepository.updateNameByNameEquals(newName, oldName);
    }

    @Transactional
    public void deleteUser(String name) {
        User userToDelete = new User();
        userToDelete.setName(name);
        userToDelete.setId(userRepository.findUserByName(name).getId());
        if (userRepository.findUserByName(name) != null) {
            userRepository.delete(userToDelete);
        }
    }

    public Integer getIdByName(String name) {
        return userRepository.getUserByName(name).getId();
    }

    public User findById(Integer userId) {
        return userRepository.findUserById(userId);
    }
}
