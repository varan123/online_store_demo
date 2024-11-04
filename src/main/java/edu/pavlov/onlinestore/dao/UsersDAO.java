package edu.pavlov.onlinestore.dao;

import edu.pavlov.onlinestore.model.User;
import edu.pavlov.onlinestore.model.UserCreationStatus;
import edu.pavlov.onlinestore.utils.PasswordUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class UsersDAO {

    /**
     * The dataSource field is used to get a connection to the database.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * The createUser method creates a new user.
     * @param user The user to create.
     * @return UserCreationStatus
     */
    public UserCreationStatus createUser(final User user) {

        if (!checkIfUserExists(user.getEmail())) {
            String encryptedPassword = PasswordUtil.encryptPassword(
                    user.getPassword(), user.getEmail());
            String query = "INSERT INTO sys_user (email, password) "
                    + "VALUES (?, ?)";
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, encryptedPassword);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return UserCreationStatus.ERROR;
            }
        } else {
            return UserCreationStatus.USER_ALREADY_EXISTS;
        }

        return UserCreationStatus.SUCCESS;
    }

    /**
     * The chaeckIfUserAndPasswordMatch method checks if the user and
     * password match.
     * @param username The user's username.
     * @param password The user's password.
     * @return true if the user and password match, false otherwise.
     */
    @SneakyThrows
    public boolean checkIfUserAndPasswordMatch(
            final String username, final String password) {
        if (!checkIfUserExists(username)) {
            return false;
        }
        String query = "SELECT password FROM sys_user WHERE email = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String encryptedPassword = resultSet.getString("password");
            return PasswordUtil.verifyPassword(
                    password, username, encryptedPassword);
        }
    }

    /**
     * The checkIfUserExists method checks if the user exists.
     * @param username The user's username.
     * @return true if the user exists, false otherwise.
     */
    @SneakyThrows
    public boolean checkIfUserExists(final String username) {
        String query = "SELECT * FROM sys_user WHERE email = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }


}
