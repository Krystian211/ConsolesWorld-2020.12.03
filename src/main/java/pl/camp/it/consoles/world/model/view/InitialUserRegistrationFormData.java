package pl.camp.it.consoles.world.model.view;

public class InitialUserRegistrationFormData {
    private String login;
    private String firstName;
    private String lastName;
    private String email;

    public InitialUserRegistrationFormData(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public InitialUserRegistrationFormData(UserRegistrationData userRegistrationData){
        this.login = userRegistrationData.getLogin();
        this.firstName = userRegistrationData.getFirstName();
        this.lastName = userRegistrationData.getLastName();
        this.email = userRegistrationData.getEmail();
    }

    public InitialUserRegistrationFormData copy(){
        InitialUserRegistrationFormData initialUserRegistrationFormData = new InitialUserRegistrationFormData(this.login,
                this.firstName,
                this.lastName,
                this.email);
        this.clear();
        return initialUserRegistrationFormData;
    }

    public void clear(){
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
    }

    public InitialUserRegistrationFormData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
