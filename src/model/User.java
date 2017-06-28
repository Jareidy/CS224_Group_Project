package model;

public class User {

    String username;
    String password;
    String emailAddress;

    public User(String username,String password,String emailAddress){
        this.username=username;
        this.password=password;
        this.emailAddress=emailAddress;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmailAddress(){
        return emailAddress;
    }
}
