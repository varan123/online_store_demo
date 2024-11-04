package edu.pavlov.onlinestore.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * The PasswordUtil class provides utility methods for password operations.
 */
public final class PasswordUtil {

    /**
     * The PasswordUtil constructor.
     */
    private PasswordUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The encryptPassword method encrypts the password.
     * @param password The password to encrypt.
     * @param username The username to encrypt.
     * @return The encrypted password.
     */
    public static String encryptPassword(final String password,
                                         final String username) {
        PasswordEncoder passwordEncoder =
                Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String encryptedPassword = passwordEncoder.encode(password + username);
        return encryptedPassword;
    }

    /**
     * The verifyPassword method verifies the password.
     * @param password The password to verify.
     * @param username The username to verify.
     * @param encryptedPassword The encrypted password.
     * @return true if the password is correct, false otherwise.
     */
    public static boolean verifyPassword(final String password,
                                         final String username,
                                         final String encryptedPassword) {
        PasswordEncoder passwordEncoder =
                Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        boolean result = passwordEncoder.matches(password + username,
                encryptedPassword);
        return result;
    }

}
