package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();
    private SessionFactory sessionFactory = util.getSession();


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE my_study_db.users (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(45) NULL,\n" +
                    "  lastName VARCHAR(45) NULL,\n" +
                    "  age INT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {}
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE users";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            System.out.printf("User с именем '%s' добавлен в базу данных\n", name);
            transaction.commit();
        } catch (Exception e) {}
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {}
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM users";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {}
    }
}
