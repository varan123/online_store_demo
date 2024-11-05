package edu.pavlov.onlinestore.tests.dao;

import edu.pavlov.onlinestore.dao.UsersDAO;
import edu.pavlov.onlinestore.model.Roles;
import edu.pavlov.onlinestore.model.User;
import edu.pavlov.onlinestore.model.UserCreationStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersDAOTest {

    @Autowired
    private UsersDAO userDAO;

    @BeforeAll
    public void prepare() {
        deleteUser();
        createUser();
    }

    public void createUser() {
        User user = new User();
        user.setEmail("email");
        user.setPassword("password");
        UserCreationStatus status = userDAO.createUser(user);
        assert status == UserCreationStatus.SUCCESS;
    }

    public void deleteUser() {
        String email = "email";
        userDAO.deleteUser(email);
    }

    @Test
    public void contextLoads() {
        assert userDAO != null;
    }

    @Test
    public void checkIfUserAndPasswordMatchTest() {
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

    @Test
    public void rolesTest() {
        userDAO.addRoleByNameToUser("email", Roles.CUSTOMER);
        Set<Roles> roles = userDAO.getUserRoles("email");
        assert roles.contains(Roles.CUSTOMER);

        userDAO.addRoleByNameToUser("email", Roles.MANAGER);
        roles = userDAO.getUserRoles("email");
        assert roles.contains(Roles.MANAGER);

        roles .clear();
        userDAO.setRolesToUser("email", roles);
        roles = userDAO.getUserRoles("email");
        assert roles.isEmpty();

    }
}
