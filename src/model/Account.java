package model;

public class Account {

    private String userName;
    private String password;
    private String emailAddress;

    public Account(String userName, String password, String emailAddress){
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
}
