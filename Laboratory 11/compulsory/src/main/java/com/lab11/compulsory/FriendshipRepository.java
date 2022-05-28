package com.lab11.compulsory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    public Integer countFriendshipsById2(Integer id2);
}
