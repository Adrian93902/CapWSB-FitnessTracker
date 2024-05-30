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

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;

    @Override
    public TrainingDto getTraining(final Long trainingId) {
        Training trainingRef = trainingRepository.getReferenceById(trainingId);
        return trainingMapper.toDto(trainingRef);
    }

    @Override
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<TrainingDto> getAllTrainingsForUser(Long userId){
        return trainingRepository.getAllTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getAllFinishedTrainingsByDate(Date afterTime) {
        return trainingRepository.getAllFinishedTrainingsByDate(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getAllTrainingsByActivityType(String activityType) {
        return trainingRepository.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

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
     * Creates a new training for the specified user.
     *
     * @param userId    the user for whom the training is created
     * @param startTime    the start time of the training
     * @param endTime      the end time of the training
     * @param activityType the type of activity for the training
     * @param distance     the distance covered during the training
     * @param averageSpeed the average speed during the training
     * @return the created Training object
     * @throws RuntimeException if the user with the specified ID is not found
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
