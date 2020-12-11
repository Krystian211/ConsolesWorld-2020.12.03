package pl.camp.it.consoles.world.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.consoles.world.dao.IUserDAO;
import pl.camp.it.consoles.world.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public User getUserByLogin(String login) {
        String sql="SELECT * FROM tuser where login=?";

        try {
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setString(1,login);

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                return this.mapResultSetToUser(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void persistUser(User user) {
        String sql="INSERT INTO tuser (login, password, firstName, lastName, email, master) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement= this.connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setBoolean(6,user.isMaster());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String sql="UPDATE tuser SET login=?, password=?, firstName=?, lastName=?, email=?, master=? WHERE id=?";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setBoolean(6,user.isMaster());
            preparedStatement.setInt(7,user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
        resultSet.getString("login"),
        resultSet.getString("password"),
        resultSet.getString("firstName"),
        resultSet.getString("lastName"),
        resultSet.getString("email"),
        resultSet.getBoolean("master"));
    }
}
