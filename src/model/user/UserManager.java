package model.user;

import java.util.ArrayList;

public class UserManager {

    public static final ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public static void clearUserData(){
        users.clear();
    }
}
