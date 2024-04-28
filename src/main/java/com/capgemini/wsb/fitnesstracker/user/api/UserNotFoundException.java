package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    private UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with a default detail message indicating the user with the specified ID was not found.
     *
     * @param id the ID of the user
     */
    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
