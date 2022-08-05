package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String url = "jdbc:mysql://localhost:3306/problem_1_1_3";
    private String username = "root";
    private String password = "root";
    private String tableName = "users_test";
    Util util = new Util(url, username, password);

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = util.connectToDb()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getTables(null, null, tableName, null);

            if (rs.next()) {
                System.out.println("Table '" + tableName + "' already exists");
            } else {
                connection.createStatement().executeUpdate(String.format("CREATE TABLE %s " +
                        "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(30), " +
                        "lastName VARCHAR(30), " +
                        "age TINYINT)", tableName));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.connectToDb()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getTables(null, null, tableName, null);

            if (rs.next()) {
                connection.createStatement().executeUpdate(String.format("DROP TABLE %s;" , tableName));
            } else {
                System.out.println(String.format("Table '%s' does not exist", tableName));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.connectToDb()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    String.format("INSERT INTO %s (name, lastName, age) VALUES (?, ?, ?);" , tableName));

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.connectToDb()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    String.format("DELETE FROM %s WHERE id = ?;" , tableName));

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = util.connectToDb()){
            ResultSet rs = connection.createStatement().executeQuery(String.format("SELECT * FROM %s;" , tableName));

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.connectToDb()){
            connection.createStatement().executeUpdate(String.format("DELETE FROM %s;" , tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
