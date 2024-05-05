package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return Optional.of(trainingRepository.getById(trainingId));
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Creates a new training for the specified user.
     *
     * @param user         the user for whom the training is created
     * @param startTime    the start time of the training
     * @param endTime      the end time of the training
     * @param activityType the type of activity for the training
     * @param distance     the distance covered during the training
     * @param averageSpeed the average speed during the training
     * @return the created Training object
     * @throws RuntimeException if the user with the specified ID is not found
     */
    @Override
    public Training createTraining(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        assert user.getId() != null;
        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));

        Training newTraining = new Training(foundUser, startTime, endTime, activityType, distance, averageSpeed);

        return trainingRepository.save(newTraining);
    }





}
