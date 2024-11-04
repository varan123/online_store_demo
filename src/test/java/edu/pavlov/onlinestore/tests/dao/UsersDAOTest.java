package edu.pavlov.onlinestore.tests.dao;

import edu.pavlov.onlinestore.dao.UsersDAO;
import edu.pavlov.onlinestore.model.User;
import edu.pavlov.onlinestore.model.UserCreationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersDAOTest {

    @Autowired
    private UsersDAO userDAO;

    @Test
    void contextLoads() {
        assert userDAO != null;
    }

    public void createUser() {
        User user = new User();
        user.setEmail("email");
        user.setPassword("password");
        UserCreationStatus status = userDAO.createUser(user);
        assert status == UserCreationStatus.SUCCESS;
    }

    @Test
    public void checkIfUserAndPasswordMatchTest() {
        createUser();
        String correctPassword = "password";
        String incorrectPassword = "incorrectPassword";
        String email = "email";
        String incorrectEmail = "incorrectEmail";
        boolean result1 = userDAO.checkIfUserAndPasswordMatch(email, correctPassword);
        assert result1;
        boolean result2 = userDAO.checkIfUserAndPasswordMatch(email, incorrectPassword);
        assert !result2;
        boolean result3 = userDAO.checkIfUserAndPasswordMatch(incorrectEmail, correctPassword);
        assert !result3;
    }
}
