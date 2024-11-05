package edu.pavlov.onlinestore.dao;

import edu.pavlov.onlinestore.model.Role;
import edu.pavlov.onlinestore.model.Roles;
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
import java.util.HashSet;
import java.util.Set;


/**
 * The UsersDAO class provides methods for user operations.
 */
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

    /**
     * The getUserRoles method returns the user's roles.
     * @param username The user's username.
     * @return List<Role>
     */
    @SneakyThrows
    public Set<Roles> getUserRoles(final String username) {
        Set<Roles> roles = new HashSet<>();
        String query = "SELECT r.ID AS ROLE_ID, r.NAME AS ROLE_NAME FROM "
                + "SYS_USER su, USER_ROLE ur, ROLE r WHERE "
                + "su.ID = ur.USER_ID AND r.ID = ur.ROLE_ID "
                + "AND su.EMAIL = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roles.add(Roles.valueOf(resultSet.getString("ROLE_NAME")));
            }
        }

        return roles;
    }

    /**
     * The setRolesToUser method sets roles to the user.
     * @param username The user's username.
     * @param roles The roles to set.
     * @return true if the roles are set, false otherwise.
     */
    @SneakyThrows
    public boolean setRolesToUser(final String username,
                                  final Set<Roles> roles) {
        //delete all roles
        String query = "DELETE FROM USER_ROLE WHERE USER_ID = "
                + "(SELECT ID FROM SYS_USER WHERE EMAIL = ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }

        //insert new roles
        query = "INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES ("
                + "(SELECT ID FROM SYS_USER WHERE EMAIL = ?), ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            for (Roles item : roles) {
                Role role = getRoleByName(item);
                preparedStatement.setString(1, username);
                preparedStatement.setLong(2, role.getId());
                preparedStatement.executeUpdate();
            }
        }
        return true;
    }

    /**
     * The getRoleByName method returns a role by its name.
     * @param roleId The role's name.
     * @return Role
     */
    public Role getRoleByName(final Roles roleId) {
        Role role = new Role();
        String query = "SELECT ID, NAME FROM ROLE WHERE NAME = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, roleId.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role.setId(resultSet.getLong("ID"));
                role.setName(resultSet.getString("NAME"));
            }
        } catch (Exception e) {
            role = null;
        }
        return role;
    }

    /**
     * The addRoleByNameToUser method adds a role by name to the user.
     * @param username The user's username.
     * @param roleId The role's name.
     * @return Set<Role>
     */
    public Set<Roles> addRoleByNameToUser(final String username,
                                         final Roles roleId) {
        Set<Roles> roles = getUserRoles(username);
        roles.add(roleId);
        setRolesToUser(username, roles);
        return roles;
    }

    /**
     * The removeAllRolesFromUser method removes all roles from the user.
     * @param username The user's username.
     */
    public void removeAllRolesFromUser(final String username) {
        Set<Roles> roles = new HashSet<>();
        setRolesToUser(username, roles);
    }

    /**
     * The getAllUsers method returns all users.
     * @return Set<String>
     */
    @SneakyThrows
    public Set<String> getAllUsers() {
        String query = "SELECT EMAIL FROM SYS_USER";
        Set<String> users = new HashSet<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(resultSet.getString("EMAIL"));
            }
        }
        return users;
    }

    /**
     * The deleteUser method deletes a user.
     * @param username The user's username.
     * @return boolean
     */
    @SneakyThrows
    public boolean deleteUser(final String username) {
        //delete all roles of the user
        removeAllRolesFromUser(username);

        //delete the user
        String query = "DELETE FROM SYS_USER WHERE EMAIL = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }

        return true;
    }
}
