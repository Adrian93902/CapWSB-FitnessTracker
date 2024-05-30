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
     * @return a list of UserDto objects representing all users
     */

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }


    @GetMapping("/findByEmail")
    public UserDto getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

    /**
     * Adds a new user.
     *
     * @param userDto the UserDto object representing the user to add
     * @return the added User object
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
     * @param userDto the UserDto object representing the user to update
     * @return the updated User object
     */
    @PutMapping("/updateUser/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId,userDto);
    }

    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deleteUser/{userId}")

    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    @GetMapping("/searchByAgeGreaterThan/{age}")
    public List<UserDto> searchUsersByAgeGreaterThan(@PathVariable int age) {
        return userService.searchUsersByAgeGreaterThan(age);
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
        return userService.getUser(userId);
    }


}