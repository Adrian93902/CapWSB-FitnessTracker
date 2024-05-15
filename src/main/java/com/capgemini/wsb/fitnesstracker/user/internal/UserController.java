package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling user-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return a list of UserDto objects representing all users
     */

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Adds a new user.
     *
     * @param userDto the UserDto object representing the user to add
     * @return the added User object
     */

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {

        System.out.println("User with email: " + userDto.email() + " passed to the request");

        User user = userMapper.toEntity(userDto);
        User userNew = userService.createUser(user);


        return userMapper.toDto(userNew);
    }

    /**
     * Updates an existing user.
     *
     * @param userDto the UserDto object representing the user to update
     * @return the updated User object
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        // Demonstracja how to use @PathVariable
        System.out.println("Updating user with ID: " + id);
        Optional<User> existingUserOptional = userService.getUser(id);
        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        User updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(id);
        return userService.updateUser(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    @GetMapping("/search")
    public List<UserDto> searchUsersByAgeGreaterThan(@RequestParam int age) {
        return userService.searchUsersByAgeGreaterThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    /**
     * Retrieves basic information about all users.
     *
     * @return a list of UserDto objects representing basic user information
     */
    @GetMapping("/basic-info")
    public List<UserSimpleDto> getAllUsersBasicInfo() {
        return userService.getUserInfoBasic();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(final Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto).orElse(null);
    }


}