package pl.camp.it.consoles.world.model.view;

import pl.camp.it.consoles.world.model.User;

public class EditUserAccountData {
    private String login;
    private String currentPassword;
    private String newPassword;
    private String repeatedNewPassword;
    private String firstName;
    private String lastName;
    private String email;

    public EditUserAccountData(String login, String currentPassword, String newPassword, String repeatedNewPassword, String firstName, String lastName, String email) {
        this.login = login;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.repeatedNewPassword = repeatedNewPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EditUserAccountData(User user) {
        this.login=user.getLogin();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }

    public EditUserAccountData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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
