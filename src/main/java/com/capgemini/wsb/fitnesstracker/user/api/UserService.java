package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;


public interface UserService extends UserProvider {

    /**
     * Creates a new user with the specified user information.
     *
     * @param userDto the user data transfer object containing user information
     * @return the created {@link UserDto} with assigned user ID and other details
     */
    UserDto createUser(UserDto userDto);
    /**
     * Updates an existing user's information.
     *
     * @param userId the ID of the user to be updated
     * @param userDto the user data transfer object containing updated user information
     * @return the updated {@link UserDto} with new details
     */
    UserDto updateUser(Long userId, UserDto userDto);

}
