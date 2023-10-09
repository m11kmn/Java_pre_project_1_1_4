package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            final String QUERY = "CREATE TABLE my_study_db.users (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(45) NULL,\n" +
                    "  lastName VARCHAR(45) NULL,\n" +
                    "  age INT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";
            statement.execute(QUERY);
        } catch (SQLException e) {
        }

    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            final String QUERY = "DROP TABLE users";
            statement.executeUpdate(QUERY);
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String QUERY = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
            System.out.printf("User с именем '%s' добавлен в базу данных\n", name);
        } catch (SQLException e) {
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getConnection().createStatement()) {
            final String QUERY = String.format("DELETE FROM users WHERE id = %d", id);
            statement.executeUpdate(QUERY);
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = util.getConnection().createStatement()) {
            final String QUERY = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            final String QUERY = "DELETE FROM users";
            statement.executeUpdate(QUERY);
        } catch (SQLException e) {
        }
    }
}
