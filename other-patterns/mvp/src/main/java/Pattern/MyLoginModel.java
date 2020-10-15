package Pattern;

public class MyLoginModel implements LoginModel {

    private String user;

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }
}