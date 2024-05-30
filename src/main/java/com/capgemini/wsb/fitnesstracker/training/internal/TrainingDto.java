package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
/**
 * Data transfer object (DTO) representing a training session.
 *
 * <p>This class represents a simplified version of a training session, containing essential details
 * such as the user performing the training, the start and end times of the session, the type of activity,
 * distance covered, and average speed.</p>
 */
@Data
@RequiredArgsConstructor
public class TrainingDto {



    private Long id;

    private UserDto user;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;
    /**
     * Constructs a new training DTO.
     *
     * @param id            the ID of the training session
     * @param user          the user performing the training
     * @param startTime     the start time of the training session
     * @param endTime       the end time of the training session
     * @param activityType  the type of activity performed during the training session
     * @param distance      the distance covered during the training session
     * @param averageSpeed  the average speed achieved during the training session
     */

    public TrainingDto(
            Long id,
            UserDto user,
            Date startTime,
            Date endTime,
            ActivityType activityType,
            double distance,
            double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

}