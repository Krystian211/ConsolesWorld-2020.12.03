package pl.camp.it.consoles.world.model.view;

import org.apache.commons.codec.digest.DigestUtils;

public class UserLoginData {
    private String login;
    private String password;

    public UserLoginData(String login, String password) {
        this.login = login;
        this.password = DigestUtils.md5Hex(password);
    }

    public UserLoginData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    public void hashAndSetPassword(String password){
        this.password=DigestUtils.md5Hex(password);
    }
}

