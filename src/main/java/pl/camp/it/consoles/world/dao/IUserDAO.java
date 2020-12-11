package pl.camp.it.consoles.world.dao;

import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.UserRegistrationData;

public interface IUserDAO {
    User getUserByLogin(String login);
    void persistUser(User user);
    void updateUser(User user);
}
