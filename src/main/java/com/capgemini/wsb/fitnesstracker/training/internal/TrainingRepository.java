package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

interface TrainingRepository extends JpaRepository<Training, Long> {


    default List<Training> getAllTrainingsForUser(Long userId){
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .collect(Collectors.toList());
    }

    default List <Training> getAllFinishedTrainingsByDate(Date endTimeTraining, Long userId){
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId)
                        &&(training.getEndTime().equals(endTimeTraining)))
                .collect(Collectors.toList());

    }

    default  List<Training> getAllTrainingsByActivityType(String activityType, Long userId){
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId)
                        &&(training.getActivityType().equals(ActivityType.valueOf(activityType.toUpperCase()))))
                .collect(Collectors.toList());
    }

}

