package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to training operations.
 *
 * <p>This class defines REST endpoints for performing CRUD operations on training sessions,
 * such as retrieving all trainings, creating a new training, updating an existing training,
 * and retrieving trainings based on various criteria such as user ID, activity type, and date.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class TrainingController {
    private final TrainingProvider trainingProvider;
    private final TrainingServiceImpl trainingService;
    /**
     * Retrieves all training sessions.
     *
     * @return a list of {@link TrainingDto} objects representing all training sessions
     */
    @GetMapping("/trainings")
    public List<TrainingDto> getAllTrainings(){
            return trainingService.getAllTrainings();
    }
    /**
     * Creates a new training session.
     *
     * @param createTraining the training session to create
     * @return the created {@link TrainingDto} object
     */
    @PostMapping("/trainings/createTraining")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto createTraining) {
        return trainingService.createTraining(createTraining);
    }
    /**
     * Retrieves a specific training session based on its ID.
     *
     * @param trainingId the ID of the training session to retrieve
     * @return the {@link TrainingDto} object representing the retrieved training session
     * @throws NotFoundException if the training session with the specified ID is not found
     */
    @GetMapping("/trainings/getTraining/{trainingId}")
    public TrainingDto getTraining(@PathVariable Long trainingId) {
        TrainingDto training = trainingService.getTraining(trainingId);
        if (training == null) {
            throw new NotFoundException("Training not found with id: " + trainingId);
        }
        return training;
    }
    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of {@link TrainingDto} objects representing all training sessions for the user
     */
    @GetMapping("/trainings/{userId}")
    public List<TrainingDto> getAllTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getAllTrainingsForUser(userId);
    }
    /**
     * Retrieves all finished training sessions after a specified date.
     *
     * @param afterTime the date after which to retrieve finished training sessions
     * @return a list of {@link TrainingDto} objects representing finished training sessions
     */
    @GetMapping("/trainings/finished/{afterTime}")
    public List<TrainingDto> getAllFinishedTrainingsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getAllFinishedTrainingsByDate(afterTime);
    }
    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param activityType the activity type to filter by
     * @return a list of {@link TrainingDto} objects representing training sessions of the specified activity type
     */
    @GetMapping("/trainings/activityType/")
    public List<TrainingDto> getAllTrainingsByActivityType(@RequestParam String activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType);
    }
    /**
     * Updates an existing training session.
     *
     * @param trainingId     the ID of the training session to update
     * @param updatedTraining the updated training session
     * @return the updated {@link TrainingDto} object
     */
    @PutMapping("/trainings/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto updatedTraining) {
        return trainingProvider.updateTraining(trainingId, updatedTraining);
    }

}
