package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between {@link User} and {@link UserDto} objects.
 *
 * <p>This class provides methods to convert {@link User} entities to {@link UserDto} data transfer objects
 * and vice versa. This is useful for separating the internal representation of the user from the
 * representation used in the API.</p>
 */
@Data
@Component
@RequiredArgsConstructor
public class UserMapper {



    /**
     * Maps a {@link User} object to a {@link UserDto} object.
     *
     * @param user the {@link User} object to map
     * @return the mapped {@link UserDto} object
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Maps a {@link UserDto} object to a {@link User} object.
     *
     * @param userDto the {@link UserDto} object to map
     * @return the mapped {@link User} object
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
