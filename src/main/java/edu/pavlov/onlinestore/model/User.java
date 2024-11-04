package edu.pavlov.onlinestore.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    /**
     * The id field represents the user's id.
     */
    private Long id;

    /**
     * The email field represents the user's email.
     */
    private String email;

    /**
     * The password field represents the user's password.
     */
    private String password;

    /**
     * The roles field represents the user's roles.
     */
    private List<Role> roles;
}
