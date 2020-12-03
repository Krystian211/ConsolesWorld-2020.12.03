package pl.camp.it.consoles.world.database;

import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditAccountData;
import pl.camp.it.consoles.world.model.view.LoginData;
import pl.camp.it.consoles.world.model.view.RegistrationData;

public interface IUserRepository {
    boolean isLoginAvailable(String login);
    boolean isEmailAvailable(String email);
    boolean isLoginAvailableExcludingUser(String login,User excludedUser);
    boolean isEmailAvailableExcludingUser(String email,User excludedUser);
    void addUser(RegistrationData registrationData);
    void addMaster(RegistrationData registrationData);
    User authenticate(LoginData loginData);
    User findByLogin(String login);
    void editUser(User loggedUser, EditAccountData editAccountData);
}
