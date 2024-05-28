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

    @GetMapping("/getAllUsers")
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


    @PostMapping("/addUser")
    @ResponseStatus
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
    @PutMapping("/updateUser/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        System.out.println("Updating user with ID: " + userId);
        Optional<User> existingUserOptional = userService.getUser(userId);
        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(userId);
        User updatedUser2 = userService.updateUser(updatedUser);
                return userMapper.toDto(updatedUser2);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @ResponseStatus
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    @GetMapping("/searchByAgeGreaterThan/{age}")
    public List<UserDto> searchUsersByAgeGreaterThan(@PathVariable int age) {
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
    @GetMapping("/listBasic-info")
    public List<UserSimpleDto> getAllUsersBasicInfo() {
        return userService.getUserInfoBasic();
    }

    @GetMapping("/getUser/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto).orElse(null);
    }


}