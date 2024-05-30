package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService extends UserProvider {

    /**
     * Creates a new user.
     *
     * @param user the user object to be created
     * @return the created user object
     */
    UserDto createUser(UserDto userDto);
    /**
     * Updates an existing user.
     *
     * @param user the user object to be updated
     * @return the updated user object
     */
    UserDto updateUser(Long userId, UserDto userDto);

}
