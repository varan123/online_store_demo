package edu.pavlov.onlinestore.model;

/**
 * The UserCreationStatus enum represents the status of user creation.
 */
public enum UserCreationStatus {
    /**
     * The SUCCESS status means that the user was created successfully.
     */
    SUCCESS,

    /**
     * The USER_ALREADY_EXISTS status means that the user already exists.
     */
    USER_ALREADY_EXISTS,

    /**
     * The ERROR status means that an error occurred during user creation.
     */
    ERROR
}
