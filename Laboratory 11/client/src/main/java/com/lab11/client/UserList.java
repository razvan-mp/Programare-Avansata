package com.lab11.client;

import java.io.Serializable;
import java.util.List;

public class UserList implements Serializable {
    private User[] userList;

    public User[] getUserList() {
        return userList;
    }

    public void setUserList(User[] userList) {
        this.userList = userList;
    }
}
