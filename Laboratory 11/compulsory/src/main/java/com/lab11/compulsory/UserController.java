package com.lab11.compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add_user")
    public void addUser(@RequestParam(name = "name") String name) {
        userService.addUser(name);
    }

    @PutMapping("/modify_user")
    public void modifyUser(@RequestParam(name = "old_name") String oldName,
                           @RequestParam(name = "new_name") String newName) {
        userService.modifyUser(oldName, newName);
    }

    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestParam(name = "name") String name) {
        userService.deleteUser(name);
    }
}
