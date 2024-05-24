package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;
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

    TrainingDto getTraining(final Long trainingId);
    List<TrainingDto> getAllTrainingsForUser(Long userId);

    List<Training> getAllTrainings();
    List<TrainingDto> getAllFinishedTrainingsByDate(Date endTimeTraining, Long userId);

    List<TrainingDto> getAllTrainingsByActivityType(String activityType, Long userId);
    TrainingDto updateTraining(Long trainingId, Long userId, TrainingDto updatedTraining);
    Training createTraining(TrainingDto createTraining);




}
