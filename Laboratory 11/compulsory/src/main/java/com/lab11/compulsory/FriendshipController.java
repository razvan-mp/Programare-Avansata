package com.lab11.compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    @PostMapping("/add_friendship")
    public String addFriendship(@RequestParam(name = "first_name") String firstName,
                              @RequestParam(name = "second_name") String secondName) {
        Integer firstId = userService.getIdByName(firstName);
        Integer secondId = userService.getIdByName(secondName);

        if (firstId == null || secondId == null)
            return "One of the users doesn't exist.";

        friendshipService.insertFriendship(firstId, secondId);
        return "Friendship between " + firstName + " and " + secondName + " added successfully.";
    }

    @GetMapping("/read_friendships")
    public String readFriendships() {
        return friendshipService.readFriendships();
    }

    @GetMapping("/find_top_k")
    public String findKMostPopular(@RequestParam(name = "k") Integer k) {
        return friendshipService.findKMostPopular(k);
    }
}
