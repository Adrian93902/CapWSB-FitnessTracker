package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.Date;
import java.util.Optional;

public class TrainingServiceImpl implements TrainingProvider {

    //private final TrainingRepository trainingRepository;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public Training createTraining(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        /*Training newTraining = new Training(user, startTime, endTime, activityType, distance, averageSpeed);
        return trainingRepository.save(newTraining);*/
        return null;
    }

}
