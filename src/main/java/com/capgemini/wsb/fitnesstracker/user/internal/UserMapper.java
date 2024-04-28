package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between User and UserDto objects.
 */
@Component
class UserMapper {



    /**
     * Maps a User object to a UserDto object.
     *
     * @param user the User object to map
     * @return the mapped UserDto object
     */
    UserDto toDto(User user) {
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
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
