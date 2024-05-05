package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between User and UserDto objects.
 */
@Component
public class UserSimpleDtoMapper {



    /**
     * Maps a User object to a UserDto object.
     *
     * @param user the User object to map
     * @return the mapped UserDto object
     */
    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());

    }

}