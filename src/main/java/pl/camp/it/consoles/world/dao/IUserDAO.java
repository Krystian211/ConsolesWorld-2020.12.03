package pl.camp.it.consoles.world.dao;

import pl.camp.it.consoles.world.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void persistUser(User user);
    void updateUser(User user);
}
