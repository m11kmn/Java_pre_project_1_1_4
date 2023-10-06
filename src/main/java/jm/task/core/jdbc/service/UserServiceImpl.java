package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userServise = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userServise.createUsersTable();
    }

    public void dropUsersTable() {
        userServise.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userServise.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userServise.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userServise.getAllUsers();
    }

    public void cleanUsersTable() {
        userServise.cleanUsersTable();
    }
}
