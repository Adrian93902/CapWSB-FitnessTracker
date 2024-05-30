package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between {@link User} and {@link UserSimpleDto} objects.
 *
 * <p>This class provides a method to convert {@link User} entities to {@link UserSimpleDto} data transfer objects.
 * It is used for mapping user information to a simplified format containing only the user's ID, first name, and last name.</p>
 */
@Component
public class UserSimpleDtoMapper {

    /**
     * Maps a {@link User} object to a {@link UserSimpleDto} object.
     *
     * @param user the {@link User} object to map
     * @return the mapped {@link UserSimpleDto} object
     */
    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());

    }

}