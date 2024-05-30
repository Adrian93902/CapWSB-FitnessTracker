package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing a user.
 *
 * <p>This class is used to transfer user data between different layers of the application.
 * It includes basic user information such as ID, first name, last name, birthdate, and email.</p>
 *
 * @param id        the unique identifier of the user, can be null for new users
 * @param firstName the first name of the user
 * @param lastName  the last name of the user
 * @param birthdate the birthdate of the user, formatted as "yyyy-MM-dd"
 * @param email     the email address of the user
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {

}
