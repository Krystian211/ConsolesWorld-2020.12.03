package pl.camp.it.consoles.world.database.implementation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.camp.it.consoles.world.database.IUserRepository;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditAccountData;
import pl.camp.it.consoles.world.model.view.LoginData;
import pl.camp.it.consoles.world.model.view.RegistrationData;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUserRepositoryImpl implements IUserRepository {
    List<User> users=new ArrayList<>();

    public ListUserRepositoryImpl() {
        addDefaultUsers();
    }

    private void addDefaultUsers(){
        addUser(new RegistrationData("krystian",
                "krystian123",
                "krystian123",
                "Krystian",
                "Bryk",
                "krystian123@wp.pl",
                false));

        addMaster(new RegistrationData("admin",
                "admin123",
                "admin123",
                "Admin",
                "Admin",
                "admin123@wp.pl",
                false));
    }

    @Override
    public boolean isLoginAvailable(String login) {
        for (User user : this.users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        for (User user : this.users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isLoginAvailableExcludingUser(String login, User excludedUser) {
        for (User user : this.users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                if(!user.getLogin().equalsIgnoreCase(excludedUser.getLogin())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isEmailAvailableExcludingUser(String email, User excludedUser) {
        for (User user : this.users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                if(!user.getLogin().equalsIgnoreCase(excludedUser.getLogin())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addUser(RegistrationData registrationData) {
        registrationData.setIsMaster(false);
        this.users.add(new User(registrationData));
    }

    @Override
    public void addMaster(RegistrationData registrationData) {
        registrationData.setIsMaster(true);
        this.users.add(new User(registrationData));
    }

    @Override
    public User authenticate(LoginData loginData) {
        for (User user : this.users) {
            if (user.getLogin().equalsIgnoreCase(loginData.getLogin())) {
                if (user.getPassword().equals(DigestUtils.md5Hex(loginData.getPassword()))) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void editUser(User loggedUser, EditAccountData editAccountData) {
        User dataBaseUser=findByLogin(loggedUser.getLogin());
        dataBaseUser.setLogin(editAccountData.getLogin());
        dataBaseUser.setFirstName(editAccountData.getFirstName());
        dataBaseUser.setLastName(editAccountData.getLastName());
        dataBaseUser.setEmail(editAccountData.getEmail());
        if (!(editAccountData.getNewPassword().equals("")&&editAccountData.getRepeatedNewPassword().equals(""))){
            dataBaseUser.setPassword(DigestUtils.md5Hex(editAccountData.getNewPassword()));
        }
    }
}
