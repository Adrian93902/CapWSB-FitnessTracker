package com.capgemini.wsb.fitnesstracker.user.api;



import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserSimpleDto;

import java.util.List;
import java.util.Optional;
/**
 * Interface providing user operations in the fitness tracker application.
 */
public interface UserProvider {
    /**
     * Returns basic information about all users.
     *
     * @return A list of UserSimpleDto objects containing basic information about users.
     */
    List<UserSimpleDto> getUserInfoBasic();

    /**
     * Returns detailed information about a user based on their identifier.
     *
     * @param userId The identifier of the user.
     * @return A UserDto object containing detailed information about the user.
     */
    UserDto getUser(Long userId);

    /**
     * Returns detailed information about a user based on their email address.
     *
     * @param email The email address of the user.
     * @return A UserDto object containing detailed information about the user.
     */
    UserDto getUserByEmail(String email);
    /**
     * Returns a list of all users.
     *
     * @return A list of UserDto objects containing detailed information about all users.
     */

    List<UserDto> findAllUsers();
    /**
     * Deletes a user based on their identifier.
     *
     * @param userId The identifier of the user to be deleted.
     */

    void deleteUser(Long userId);

    /**
     * Searches for users who are older than the specified age.
     *
     * @param age The age that users should be older than.
     * @return A list of UserDto objects representing users who are older than the specified age.
     */

    List<UserDto> searchUsersByAgeGreaterThan(int age);

}
