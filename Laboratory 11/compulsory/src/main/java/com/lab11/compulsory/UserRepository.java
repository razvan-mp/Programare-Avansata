package com.lab11.compulsory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByName(String name);

    @Transactional
    @Modifying
    @Query("update User u set u.name = ?1 where u.name = ?2")
    void updateNameByNameEquals(String oldName, String newName);

    public User findUserByName(String name);

    public User findUserById(Integer id);
}
