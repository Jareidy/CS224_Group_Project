package model;

import java.util.HashMap;

public class AccountStorage {
    HashMap<String, Account> user = new HashMap<>();

    public void addUsers(String password, Account accountName){
        user.put(password, accountName);
    }

    public Account getUsers(String password){
        if(user.containsKey(password)){
            return user.get(password);
        }else{
            return null;
        }
    }
}
