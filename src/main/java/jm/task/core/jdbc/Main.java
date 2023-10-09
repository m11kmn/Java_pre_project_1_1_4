package jm.task.core.jdbc;

import com.sun.xml.bind.util.Which;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Cyril", "А", (byte) 37);
        userService.saveUser("Ray", "G", (byte) 35);

        userService.saveUser("Archer", "S", (byte) 34);
        userService.saveUser("Lana", "C", (byte) 36);

        userService.getAllUsers().stream().forEach(System.out::println);

//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
