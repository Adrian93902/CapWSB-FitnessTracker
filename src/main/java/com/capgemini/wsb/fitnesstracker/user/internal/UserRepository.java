package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }
    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {

        return findAll().stream()
                        .filter(user -> findUser(user, email))
                        .findFirst();
    }

    private boolean findUser (User user, String email)
    {
        if (user.getEmail().contains(email) || user.getEmail().equalsIgnoreCase(email)){
            return true;
        }
        return false;

    }

}
