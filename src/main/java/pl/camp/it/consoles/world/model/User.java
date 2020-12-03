package pl.camp.it.consoles.world.model;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.consoles.world.model.view.RegistrationData;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean master;

    public User(String login, String password, String firstName, String lastName, String email, boolean master) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.master = master;
    }

    public User(RegistrationData registrationData) {
        this.login = registrationData.getLogin();
        this.password = DigestUtils.md5Hex(registrationData.getPassword());
        this.firstName = registrationData.getFirstName();
        this.lastName = registrationData.getLastName();
        this.email = registrationData.getEmail();
        this.master = registrationData.getIsMaster();
    }

    public User() {
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
