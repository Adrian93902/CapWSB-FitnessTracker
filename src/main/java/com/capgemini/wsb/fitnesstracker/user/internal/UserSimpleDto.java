package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing simplified user information.
 *
 * <p>This class is used to transfer basic user data between different layers of the application.
 * It includes only the essential information such as ID, first name, and last name.</p>
 *
 * @param Id        the unique identifier of the user, can be null for new users
 * @param firstName the first name of the user
 * @param lastName  the last name of the user
 */
public record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {

}