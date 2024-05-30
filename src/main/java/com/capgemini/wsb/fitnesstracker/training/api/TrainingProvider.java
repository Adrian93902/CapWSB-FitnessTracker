package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.Date;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.List;
import java.util.Optional;
/**
 * Interface defining operations for providing training-related services.
 *
 * <p>This interface declares methods for retrieving, creating, and updating training sessions.
 * It also includes methods for retrieving all trainings, trainings for a specific user,
 * and trainings based on various criteria such as activity type and date.</p>
 */
public interface TrainingProvider {

    /**
     * Retrieves a training session based on its ID.
     *
     * @param trainingId the ID of the training session to retrieve
     * @return an {@link Optional} containing the located {@link TrainingDto}, or {@link Optional#empty()} if not found
     */

    TrainingDto getTraining(final Long trainingId);
    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of {@link TrainingDto} objects representing all training sessions for the user
     */
    List<TrainingDto> getAllTrainingsForUser(Long userId);
    /**
     * Retrieves all training sessions.
     *
     * @return a list of {@link TrainingDto} objects representing all training sessions
     */

    List<TrainingDto> getAllTrainings();
    /**
     * Retrieves all finished training sessions after a specified date.
     *
     * @param afterTime the date after which to retrieve finished training sessions
     * @return a list of {@link TrainingDto} objects representing finished training sessions
     */
    List<TrainingDto> getAllFinishedTrainingsByDate(Date afterTime);

    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param activityType the activity type to filter by
     * @return a list of {@link TrainingDto} objects representing training sessions of the specified activity type
     */

    List<TrainingDto> getAllTrainingsByActivityType(String activityType);
    /**
     * Updates an existing training session.
     *
     * @param trainingId     the ID of the training session to update
     * @param updatedTraining the updated training session
     * @return the updated {@link TrainingDto} object
     */
    TrainingDto updateTraining(Long trainingId, TrainingDto updatedTraining);
    /**
     * Creates a new training session.
     *
     * @param createTraining the training session to create
     * @return the created {@link TrainingDto} object
     */
    TrainingDto createTraining(TrainingDto createTraining);




}
