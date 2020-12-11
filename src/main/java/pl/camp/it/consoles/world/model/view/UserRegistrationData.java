package pl.camp.it.consoles.world.model.view;

public class UserRegistrationData {
    private String login;
    private String password;
    private String repeatedPassword;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isMaster;

    public UserRegistrationData() {
    }

    public UserRegistrationData(InitialUserRegistrationFormData initialUserRegistrationFormData) {
        this.login = initialUserRegistrationFormData.getLogin();
        this.firstName = initialUserRegistrationFormData.getFirstName();
        this.lastName = initialUserRegistrationFormData.getLastName();
        this.email = initialUserRegistrationFormData.getEmail();
    }

    public UserRegistrationData(UserRegistrationData userRegistrationData) {
        this.login = userRegistrationData.getLogin();
        this.password = userRegistrationData.getPassword();
        this.repeatedPassword = userRegistrationData.getRepeatedPassword();
        this.firstName = userRegistrationData.getFirstName();
        this.lastName = userRegistrationData.getLastName();
        this.email = userRegistrationData.getEmail();
        this.isMaster= userRegistrationData.getIsMaster();
    }

    public UserRegistrationData(String login, String password, String repeatedPassword, String firstName, String lastName, String email, boolean isMaster) {
        this.login = login;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isMaster = isMaster;
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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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

    public boolean getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }
}