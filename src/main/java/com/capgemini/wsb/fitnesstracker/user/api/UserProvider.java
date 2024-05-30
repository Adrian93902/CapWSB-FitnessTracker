package com.capgemini.wsb.fitnesstracker.user.api;



import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserSimpleDto;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    List<UserSimpleDto> getUserInfoBasic();

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    UserDto getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    UserDto getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<UserDto> findAllUsers();

    /**
     * Deletes a user based on their ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUser(Long userId);


    /**
     * Searches for users whose age is greater than the specified value.
     *
     * @param age the age value to compare
     * @return a list of users whose age is greater than the specified value
     */
    List<UserDto> searchUsersByAgeGreaterThan(int age);

}
