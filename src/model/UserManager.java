package model;

import java.util.ArrayList;

/**
 * Created by Jordan Reidy on 6/28/2017.
 */
public class UserManager {

    public static final ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static ArrayList returnUsers(){
        return users;
    }
}
