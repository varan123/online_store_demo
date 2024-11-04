package edu.pavlov.onlinestore.tests.unit.model;

import edu.pavlov.onlinestore.model.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RoleTest {

    private static Role role;

    @Test
    public void testRole1() {
        role = new Role();
        role.setId(1L);
        role.setName("role");

        assert role.getId() == 1L;
        assert role.getName().equals("role");
    }

    @Test
    public void testRole2() {
        role = new Role(1L, "role");

        assert role.getId() == 1L;
        assert role.getName().equals("role");
    }

}
