package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * Component responsible for mapping between Training and TrainingDto objects.
 *
 * <p>This class provides methods for mapping Training objects to TrainingDto objects and vice versa.
 * It uses a UserMapper to handle user mapping.</p>
 */
@Component
@RequiredArgsConstructor
public class TrainingMapper {
    private final UserMapper userMapper;
    /**
     * Maps a Training object to a TrainingDto object.
     *
     * @param training the Training object to map
     * @return the mapped TrainingDto object
     */
    TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setUser(userMapper.toDto(training.getUser()));
        trainingDto.setStartTime(training.getStartTime());
        trainingDto.setEndTime(training.getEndTime());
        trainingDto.setActivityType(training.getActivityType());
        trainingDto.setDistance(training.getDistance());
        trainingDto.setAverageSpeed(training.getAverageSpeed());

        return trainingDto;

    }
    /**
     * Maps a TrainingDto object to a Training object.
     *
     * @param trainingDto the TrainingDto object to map
     * @param foundUser   the User object associated with the training
     * @return the mapped Training object
     */
    Training toEntity(TrainingDto trainingDto, User foundUser){

        if (trainingDto == null) {
            return null;
        }
        Training newTraining = new Training();
        newTraining.setUser(foundUser);
        newTraining.setStartTime(trainingDto.getStartTime());
        newTraining.setEndTime(trainingDto.getEndTime());
        newTraining.setActivityType(trainingDto.getActivityType());
        newTraining.setDistance(trainingDto.getDistance());
        newTraining.setAverageSpeed(trainingDto.getAverageSpeed());

        return newTraining;

    }
}

