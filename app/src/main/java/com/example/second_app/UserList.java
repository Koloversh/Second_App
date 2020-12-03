package com.example.second_app;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList userList;
    private List users;
    public static UserList get(){
        if(userList == null){
            userList = new UserList();
        }
        return userList;
    }
    private UserList(){
        users = new ArrayList();
        for(int i=0; i<100; i++){
            User user = new User();
            user.setUserName("ИМЯ_"+i);
            user.setUserLastName("Фамилия_"+i);
            users.add(user);
        }
    }
    public List getUsers(){
        return users;
    }
}
