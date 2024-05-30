package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingMapper;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Date;
import java.util.stream.Collectors;
/**
 * Service implementation class for managing training operations.
 *
 * <p>This class provides methods to retrieve, create, update, and delete training sessions.
 * It interacts with the TrainingRepository and UserRepository to access and manipulate training and user data.
 * The TrainingMapper is used for mapping between Training and TrainingDto objects.</p>
 */
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;
    /**
     * Retrieves a training session by its ID.
     *
     * @param trainingId the ID of the training session to retrieve
     * @return the retrieved TrainingDto object
     */
    @Override
    public TrainingDto getTraining(final Long trainingId) {
        Training trainingRef = trainingRepository.getReferenceById(trainingId);
        return trainingMapper.toDto(trainingRef);
    }
    /**
     * Retrieves all training sessions.
     *
     * @return a list of all TrainingDto objects
     */
    @Override
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of TrainingDto objects associated with the specified user
     */
    @Override
    public List<TrainingDto> getAllTrainingsForUser(Long userId){
        return trainingRepository.getAllTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all finished training sessions before or on the specified end time.
     *
     * @param afterTime the end time of the training sessions
     * @return a list of finished TrainingDto objects
     */
    @Override
    public List<TrainingDto> getAllFinishedTrainingsByDate(Date afterTime) {
        return trainingRepository.getAllFinishedTrainingsByDate(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param activityType the activity type to filter by
     * @return a list of TrainingDto objects with the specified activity type
     */
    @Override
    public List<TrainingDto> getAllTrainingsByActivityType(String activityType) {
        return trainingRepository.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Updates an existing training session.
     *
     * @param trainingId     the ID of the training session to update
     * @param updatedTraining the updated TrainingDto object
     * @return the updated TrainingDto object
     * @throws RuntimeException if the training or user is not found, or if the user is not authorized to update the training
     */
    @Override
    public TrainingDto updateTraining(Long trainingId, TrainingDto updatedTraining) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + trainingId));

        if (!training.getUser().getId().equals(updatedTraining.getUser().id())) {
            throw new RuntimeException("User is not authorized to update this training");
        }

        training.setStartTime(updatedTraining.getStartTime());
        training.setEndTime(updatedTraining.getEndTime());
        training.setActivityType(updatedTraining.getActivityType());
        training.setDistance(updatedTraining.getDistance());
        training.setAverageSpeed(updatedTraining.getAverageSpeed());

        Training updatedTrainingEntity = trainingRepository.save(training);
        return trainingMapper.toDto(updatedTrainingEntity);
    }



    /**
     * Creates a new training session.
     *
     * @param createTraining the TrainingDto object representing the new training session
     * @return the created TrainingDto object
     * @throws RuntimeException if the user is not found
     */
    @Override
    public TrainingDto createTraining(TrainingDto createTraining) {
        User foundUser = userRepository.findById(createTraining.getUser().id())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createTraining.getUser().id()));

        Training newTraining = trainingMapper.toEntity(createTraining, foundUser);

        Training training = trainingRepository.save(newTraining);
        return trainingMapper.toDto(training);


    }





}
