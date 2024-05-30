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

/**
 * Service implementation for managing user-related operations.
 *
 * <p>This service provides methods for creating, updating, retrieving, and deleting users.
 * It also includes methods for searching users by age and retrieving basic user information.</p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Creates a new user.
     *
     * @param userDto the user data transfer object to be created
     * @return the created user data transfer object
     */

    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("Creating User {}", userDto);
        //if (userDto.getId() != null) {
        //    throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        //}

        User newUser = userMapper.toEntity(userDto);
        User newUserDto =  userRepository.save(newUser);
        return userMapper.toDto(newUserDto);
    }
    /**
     * Retrieves basic information about all users.
     *
     * @return a list of {@link UserSimpleDto} objects representing basic user information
     */
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
     * @return the user data transfer object if found, otherwise null
     */

    @Override
    public UserDto getUser(final Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElse(null);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return the user data transfer object if found, otherwise null
     */

    @Override
    public UserDto getUserByEmail(final String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElse(null);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of user data transfer objects representing all users
     */

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
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
     * @return a list of user data transfer objects representing users whose age is greater than the specified value
     */


    @Override
    public List<UserDto> searchUsersByAgeGreaterThan(int age) {
        log.info("Searching users by age greater than: {}", age);
        return userRepository.searchUsersByAgeGreaterThan(age)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing user.
     *
     * @param userId  the ID of the user to update
     * @param userDto the user data transfer object containing updated information
     * @return the updated user data transfer object
     */

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {


        Optional<User> existingUserOptional = userRepository.findById(userId);

        User updatedUser = existingUserOptional.map(user -> {
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setBirthdate(userDto.birthdate());
            user.setEmail(userDto.email());
            return user;
        }).orElse(null);

        User savedUser = userRepository.save(updatedUser);
        return userMapper.toDto(savedUser);
    }
}