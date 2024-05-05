package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.Date;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    // TODO fix User
    Optional<Training> getTraining(Long trainingId);

    List<Training> getAllTrainings();


    /**
     * Creates a new training.
     *
     * @param user         the user for whom the training is created
     * @param startTime    the start time of the training
     * @param endTime      the end time of the training
     * @param activityType the type of activity for the training
     * @param distance     the distance covered during the training
     * @param averageSpeed the average speed during the training
     * @return the created Training object
     */

    Training createTraining(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed);
}
