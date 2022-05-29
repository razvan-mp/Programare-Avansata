package com.lab11.compulsory.repos;

import com.lab11.compulsory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByName(String name);

    @Transactional
    @Modifying
    @Query("update User u set u.name = ?1 where u.name = ?2")
    void updateNameByNameEquals(String oldName, String newName);

    User findUserByName(String name);

    User findUserById(Integer id);
}
