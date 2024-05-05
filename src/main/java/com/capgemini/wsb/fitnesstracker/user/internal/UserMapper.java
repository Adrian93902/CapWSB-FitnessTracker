package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between User and UserDto objects.
 */
@Data
@Component
@RequiredArgsConstructor
public class UserMapper {



    /**
     * Maps a User object to a UserDto object.
     *
     * @param user the User object to map
     * @return the mapped UserDto object
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Maps a UserDto object to a User object.
     *
     * @param userDto the UserDto object to map
     * @return the mapped User object
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
