package edu.pavlov.onlinestore.tests.unit.utils;

import edu.pavlov.onlinestore.utils.PasswordUtil;
import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

    @Test
    public void testEncryptPassword() {
        String password = "password";
        String username = "username";
        String incorrectPassword = "incorrectPassword";
        String incorrectUsername = "incorrectUsername";
        String encryptedPassword = PasswordUtil.encryptPassword(password, username);
        String incorrectEncryptedPassword = PasswordUtil.encryptPassword(incorrectPassword, incorrectUsername);

        assert PasswordUtil.verifyPassword(password, username, encryptedPassword);
        assert !PasswordUtil.verifyPassword(password, incorrectUsername, encryptedPassword);
        assert !PasswordUtil.verifyPassword(incorrectPassword, username, encryptedPassword);
        assert !PasswordUtil.verifyPassword(password, username, incorrectEncryptedPassword);
    }

}
