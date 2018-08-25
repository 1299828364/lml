package model;

public class UInfo extends Entity {
    String  user;
    String code;

    @Override
    public String getType(){
        return "user";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String password) {
        this.code = password;
    }
}
