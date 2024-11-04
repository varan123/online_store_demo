package edu.pavlov.onlinestore.dao;

import edu.pavlov.onlinestore.model.UserCreationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDAO {

    /**
     * The dataSource field is used to get a connection to the database.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * The createUser method creates a new user.
     * @param username The user's username.
     * @param notEncryptedPassword The user's password.
     * @return UserCreationStatus
     */
    public UserCreationStatus createUser(final String username,
                                         final String notEncryptedPassword) {
        return UserCreationStatus.SUCCESS;
    }


}
