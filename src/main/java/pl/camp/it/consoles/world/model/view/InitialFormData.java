package pl.camp.it.consoles.world.model.view;

public class InitialFormData {
    private String login;
    private String firstName;
    private String lastName;
    private String email;

    public InitialFormData(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public InitialFormData(RegistrationData registrationData){
        this.login = registrationData.getLogin();
        this.firstName = registrationData.getFirstName();
        this.lastName = registrationData.getLastName();
        this.email = registrationData.getEmail();
    }

    public InitialFormData copy(){
        InitialFormData initialFormData= new InitialFormData(this.login,
                this.firstName,
                this.lastName,
                this.email);
        this.clear();
        return initialFormData;
    }

    public void clear(){
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
    }

    public InitialFormData() {
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
