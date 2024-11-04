package edu.pavlov.onlinestore.tests.unit.model;

import edu.pavlov.onlinestore.model.Role;
import edu.pavlov.onlinestore.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTest {

    private static User user;

    @BeforeAll
    public static void setUp() {
        user = new User();
    }

    @Test
    public void testUser() {
        user.setId(1L);
        user.setEmail("email");
        user.setPassword("password");

        Role role1 = new Role(1L, "role");
        Role role2 = new Role(2L, "role2");
        List<Role> roles = List.of(role1, role2);
        user.setRoles(roles);

        assert user.getId() == 1L;
        assert user.getEmail().equals("email");
        assert user.getPassword().equals("password");
        assert user.getRoles().equals(roles);
    }

}
