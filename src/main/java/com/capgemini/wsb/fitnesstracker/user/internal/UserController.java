package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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



    /**
     * Retrieves all users.
     *
     * @return a list of {@link UserDto} objects representing all users
     */

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return the {@link UserDto} object representing the user
     */
    @GetMapping("/findByEmail/{email}")
    public UserDto getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    /**
     * Adds a new user.
     *
     * @param userDto the {@link UserDto} object representing the user to add
     * @return the added {@link UserDto} object
     */


    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        System.out.println("User with email: " + userDto.email() + " passed to the request");
        return userService.createUser(userDto);
    }

    /**
     * Updates an existing user.
     *
     * @param userId  the ID of the user to update
     * @param userDto the {@link UserDto} object representing the updated user information
     * @return the updated {@link UserDto} object
     */
    @PutMapping("/updateUser/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId,userDto);
    }


    /**
     * Deletes a user.
     *
     * @param userId the ID of the user to delete
     */
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    /**
     * Searches for users older than a specified age.
     *
     * @param age the minimum age of the users to search for
     * @return a list of {@link UserDto} objects representing users older than the specified age
     */
    @GetMapping("/searchByAgeGreaterThan/{age}")
    public List<UserDto> searchUsersByAgeGreaterThan(@PathVariable int age) {
        return userService.searchUsersByAgeGreaterThan(age);
    }
    /**
     * Retrieves basic information about all users.
     *
     * @return a list of {@link UserSimpleDto} objects representing basic user information
     */
    @GetMapping("/listBasic-info")
    public List<UserSimpleDto> getAllUsersBasicInfo() {
        return userService.getUserInfoBasic();
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the {@link UserDto} object representing the user
     */
    @GetMapping("/getUser/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }


}