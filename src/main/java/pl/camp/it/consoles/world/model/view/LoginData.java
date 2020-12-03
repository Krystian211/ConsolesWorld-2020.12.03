package pl.camp.it.consoles.world.model.view;

public class LoginData {
    private String login;
    private String password;
    private boolean master;

    public LoginData(String login, String password, boolean master) {
        this.login = login;
        this.password = password;
        this.master = master;
    }

    public LoginData() {
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
        this.password = password;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }
}

