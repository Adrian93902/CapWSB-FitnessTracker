package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository interface for managing {@link User} entities.
 *
 * <p>This interface extends {@link JpaRepository} to provide CRUD operations on {@link User} entities.
 * It also includes custom query methods for additional functionality.</p>
 */

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches for users whose age is greater than the specified value.
     *
     * @param age the age value to compare
     * @return a list of users whose age is greater than the specified value
     */
    default List<User> searchUsersByAgeGreaterThan(int age) {
        LocalDate today = LocalDate.now();
        return findAll().stream()
                .filter(user -> calculateAge(user.getBirthdate(), today) > age)
                .collect(Collectors.toList());
    }
    /**
     * Calculates the age of a user based on their birthdate and the current date.
     *
     * @param birthDate   the birthdate of the user
     * @param currentDate the current date
     * @return the calculated age of the user
     */
    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }
    /**
     * Searches for a user by their email address.
     *
     * @param email the email address of the user to search for
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if no user is found
     */
    default Optional<User> findByEmail(String email) {

        return findAll().stream()
                        .filter(user -> findUser(user, email))
                        .findFirst();
    }
    /**
     * Checks if the given user's email matches the specified email.
     *
     * @param user  the user to check
     * @param email the email to match
     * @return true if the user's email matches the specified email, false otherwise
     */
    private boolean findUser (User user, String email)
    {
        if (user.getEmail().contains(email) || user.getEmail().equalsIgnoreCase(email)){
            return true;
        }
        return false;

    }

}
