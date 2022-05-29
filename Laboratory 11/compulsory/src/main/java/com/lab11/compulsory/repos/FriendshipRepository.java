package com.lab11.compulsory.repos;

import com.lab11.compulsory.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Integer countFriendshipsById2(Integer id2);
}
