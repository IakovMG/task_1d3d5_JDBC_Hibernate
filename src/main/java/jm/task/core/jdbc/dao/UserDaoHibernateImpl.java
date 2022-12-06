package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;
    private static Session session;


    public UserDaoHibernateImpl() {

        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS User " +
                "(ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(200)," +
                "LASTNAME VARCHAR(200)," +
                "AGE TINYINT)";

        session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS User";

        session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();

    }

    @Override
    public void removeUserById(long id) {

        session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();

    }

    @Override
    public List<User> getAllUsers() {


        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).list();
        transaction.commit();
        return users;

    }

    @Override
    public void cleanUsersTable() {

        String sql = "DELETE FROM User";

        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();


    }

}
