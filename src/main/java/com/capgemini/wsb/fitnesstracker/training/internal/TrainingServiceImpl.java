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
        TrainingDto trainingToDto = trainingMapper.toDto(trainingRef);
        return trainingToDto;
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }
    @Override
    public List<TrainingDto> getAllTrainingsForUser(Long userId){
        return trainingRepository.getAllTrainingsForUser(userId).stream().map(training ->  trainingMapper.toDto(training)).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getAllFinishedTrainingsByDate(Date endTimeTraining, Long userId) {
        return trainingRepository.getAllFinishedTrainingsByDate(endTimeTraining,userId).stream()
                .map(training ->  trainingMapper.toDto(training))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getAllTrainingsByActivityType(String activityType, Long userId) {

        return trainingRepository.getAllTrainingsByActivityType(activityType,userId).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDto updateTraining(Long trainingId, Long userId, TrainingDto updatedTraining) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + trainingId));

        if (!training.getUser().getId().equals(userId)) {
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
    public Training createTraining(TrainingDto createTraining) {
        User foundUser = userRepository.findById(createTraining.getUser().Id())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createTraining.getUser().Id()));

        Training newTraining = new Training(foundUser, createTraining.getStartTime(), createTraining.getEndTime(), ActivityType.valueOf(createTraining.getActivityType().toString().toUpperCase()), createTraining.getDistance(), createTraining.getAverageSpeed());

        return trainingRepository.save(newTraining);
    }





}
