package pl.camp.it.consoles.world.model.view;

import pl.camp.it.consoles.world.model.User;

public class EditAccountData {
    private String login;
    private String oldPassword;
    private String newPassword;
    private String repeatedNewPassword;
    private String firstName;
    private String lastName;
    private String email;

    public EditAccountData(String login, String oldPassword, String newPassword, String repeatedNewPassword, String firstName, String lastName, String email) {
        this.login = login;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatedNewPassword = repeatedNewPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EditAccountData(User user) {
        this.login=user.getLogin();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }

    public EditAccountData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatedNewPassword() {
        return repeatedNewPassword;
    }

    public void setRepeatedNewPassword(String repeatedNewPassword) {
        this.repeatedNewPassword = repeatedNewPassword;
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
