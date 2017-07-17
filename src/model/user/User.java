package model.user;

public class User {

    String username;
    String password;
    String emailAddress;
    String securityQuestion;
    String answer;

    public User(UserBuilder userBuilder){
        this.username=userBuilder.username;
        this.password=userBuilder.password;
        this.emailAddress=userBuilder.email;
        this.securityQuestion=userBuilder.securityQuestion;
        this.answer=userBuilder.answer;
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

    public String getSecurityQuestion(){
        return securityQuestion;
    }

    public String getAnswer(){
        return answer;
    }

    public void resetPassword(String password) {
        password.equals(password);
    }
}
