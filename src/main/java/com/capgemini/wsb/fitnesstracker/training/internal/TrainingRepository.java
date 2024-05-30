package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

interface TrainingRepository extends JpaRepository<Training, Long> {


    default List<Training> getAllTrainingsForUser(Long userId){
        return findAll()
                .stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .collect(Collectors.toList());
    }

    default List <Training> getAllFinishedTrainingsByDate(Date endTimeTraining){
        return findAll()
                .stream()
                .filter(training -> (training.getEndTime().before(Date.from(endTimeTraining.toInstant().plus(Duration.ofDays(1))))))
                .collect(Collectors.toList());

    }

    default  List<Training> getAllTrainingsByActivityType(String activityType){
        return findAll()
                .stream()
                .filter(training -> (training.getActivityType().equals(ActivityType.valueOf(activityType.toUpperCase()))))
                .collect(Collectors.toList());
    }

}

