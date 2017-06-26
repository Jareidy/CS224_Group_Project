
package model;

import java.util.HashMap;

public class AccountStorage {
    HashMap<String, Account> user = new HashMap();

    public AccountStorage() {
    }

    public void addUsers(String password, Account accountName) {
        this.user.put(password, accountName);
    }

    public Account getUsers(String password) {
        return this.user.containsKey(password)?(Account)this.user.get(password):null;
    }
}
