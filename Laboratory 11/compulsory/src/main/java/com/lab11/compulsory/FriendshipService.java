package com.lab11.compulsory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void insertFriendship(Integer firstId, Integer secondId) {
        Friendship firstFriendship = new Friendship();
        firstFriendship.setId1(firstId);
        firstFriendship.setId2(secondId);
        friendshipRepository.save(firstFriendship);

        Friendship secondFriendship = new Friendship();
        secondFriendship.setId1(secondId);
        secondFriendship.setId2(firstId);
        friendshipRepository.save(secondFriendship);
    }

    @Transactional
    public String readFriendships() {
        StringBuilder friendships = new StringBuilder();

        List<Friendship> friendshipList = friendshipRepository.findAll();

        for (Friendship friendship : friendshipList) {
            friendships.append(userService.findById(friendship.getId1()).getName());
            friendships.append(" is friends with ");
            friendships.append(userService.findById(friendship.getId2()).getName());
            friendships.append("\n");
        }
        return friendships.toString();
    }

    @Transactional
    public String findKMostPopular(Integer k) {
        StringBuilder mostPopular = new StringBuilder();

        Map<String, Integer> friendshipCount = new HashMap<>();
        for (var user : userService.getAllUsers()) {
            friendshipCount.put(user.getName(), friendshipRepository.countFriendshipsById2(user.getId()));
        }

        Map<String, Integer> topKPeople = friendshipCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Integer count = 1;
        for (var person : topKPeople.entrySet()) {
            mostPopular.append(count).append(". ");
            mostPopular.append(person.getKey()).append(" has ").append(person.getValue()).append(" friends");
            mostPopular.append("\n");
            count++;
        }

        return mostPopular.toString();
    }
}
