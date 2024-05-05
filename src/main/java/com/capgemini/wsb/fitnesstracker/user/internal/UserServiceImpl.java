package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user the user object to be created
     * @return the created user object
     * @throws IllegalArgumentException if the user object already has a database ID
     */

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public List<UserSimpleDto> getUserInfoBasic() {
        log.info("Getting basic user info for all users.");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the user object if found, otherwise empty
     */

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return an Optional containing the user object if found, otherwise empty
     */

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
    }

    /**
     * Searches for users whose age is greater than the specified value.
     *
     * @param age the age value to compare
     * @return a list of users whose age is greater than the specified value
     */


    @Override
    public List<User> searchUsersByAgeGreaterThan(int age) {
        log.info("Searching users by age greater than: {}", age);
        return userRepository.searchUsersByAgeGreaterThan(age);
    }

    /**
     * Updates an existing user.
     *
     * @param user the user object to be updated
     * @return the updated user object
     * @throws IllegalArgumentException if the user object does not have a database ID
     */

    @Override
    public User updateUser(User user) {
        log.info("Updating user with ID: {}", user.getId());
        if (user.getId() == null) {
            throw new IllegalArgumentException("User does not have a DB ID, cannot be updated!");
        }
        return userRepository.save(user);
    }
}