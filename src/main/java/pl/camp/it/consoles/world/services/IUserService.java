package pl.camp.it.consoles.world.services;

import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditUserAccountData;
import pl.camp.it.consoles.world.model.view.UserLoginData;
import pl.camp.it.consoles.world.model.view.UserRegistrationData;

import java.util.List;

public interface IUserService {
    User authenticate(UserLoginData userLoginData);
    List<String> registerUser(UserRegistrationData userRegistrationData);
    List<String> updateUserData(EditUserAccountData editUserAccountData);

}
