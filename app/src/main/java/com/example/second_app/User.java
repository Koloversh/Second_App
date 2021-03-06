package com.example.second_app;

import java.util.UUID;
import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String userLastName;
    private UUID uuid;

    public User() {
        this(UUID.randomUUID()); // При создании нового пользователя
    }
    public User(UUID uuid){
        this.uuid = uuid; //При создании существующего пользователя

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public UUID getUuid() {
        return uuid;
    }
}
