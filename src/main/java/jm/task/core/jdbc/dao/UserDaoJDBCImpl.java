package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCr = "CREATE TABLE IF NOT EXISTS User " +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                " lastname VARCHAR(255)," +
                " age TINYINT)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCr)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        String sqlDr = "DROP TABLE IF EXISTS User";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDr)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlIn = "INSERT INTO User (name, lastname, age) VALUES(?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlIn)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String sqlD = "DELETE FROM User WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlD)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String sqlS = "SELECT id, name, lastname, age FROM User";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() {

        String sqlD = "DELETE FROM User";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlD)) {
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getDaoConnection(){
        return connection;
    }

}
