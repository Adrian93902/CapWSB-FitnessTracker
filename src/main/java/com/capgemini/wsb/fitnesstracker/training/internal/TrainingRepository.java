package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;
/**
 * Repository interface for accessing training data from the database.
 *
 * <p>This interface extends JpaRepository to inherit basic CRUD operations for Training entities.
 * Additionally, it provides custom query methods for retrieving training sessions based on various criteria,
 * such as user ID, end time, and activity type.</p>
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of training sessions associated with the specified user
     */
    default List<Training> getAllTrainingsForUser(Long userId){
        return findAll()
                .stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all finished training sessions before or on the specified end time.
     *
     * @param endTimeTraining the end time of the training sessions
     * @return a list of finished training sessions
     */
    default List <Training> getAllFinishedTrainingsByDate(Date endTimeTraining){
        return findAll()
                .stream()
                .filter(training -> (training.getEndTime().before(Date.from(endTimeTraining.toInstant().plus(Duration.ofDays(1))))))
                .collect(Collectors.toList());

    }
    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param activityType the activity type to filter by
     * @return a list of training sessions with the specified activity type
     */
    default  List<Training> getAllTrainingsByActivityType(String activityType){
        return findAll()
                .stream()
                .filter(training -> (training.getActivityType().equals(ActivityType.valueOf(activityType.toUpperCase()))))
                .collect(Collectors.toList());
    }

}

