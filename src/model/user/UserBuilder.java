package model.user;

public final class UserBuilder {

    protected String username;
    protected String password;
    protected String email;
    protected String securityQuestion;
    protected String answer;

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setSecurityQuestion(String securityQuestion){
        this.securityQuestion=securityQuestion;
    }

    public void setAnswer(String answer){
        this.answer=answer;
    }

    public User build(){
        return new User(this);
    }


}
