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


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class TrainingController {
    private final TrainingProvider trainingProvider;
    private final TrainingServiceImpl trainingService;

    @GetMapping("/trainings")
    public List<TrainingDto> getAllTrainings(){
            return trainingService.getAllTrainings();
    }
    @PostMapping("/trainings/createTraining")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto createTraining) {
        return trainingService.createTraining(createTraining);
    }
    @GetMapping("/trainings/getTraining/{trainingId}")
    public TrainingDto getTraining(@PathVariable Long trainingId) {
        TrainingDto training = trainingService.getTraining(trainingId);
        if (training == null) {
            throw new NotFoundException("Training not found with id: " + trainingId);
        }
        return training;
    }
    @GetMapping("/trainings/{userId}")
    public List<TrainingDto> getAllTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getAllTrainingsForUser(userId);
    }

    @GetMapping("/trainings/finished/{afterTime}")
    public List<TrainingDto> getAllFinishedTrainingsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getAllFinishedTrainingsByDate(afterTime);
    }

    @GetMapping("/trainings/activityType")
    public List<TrainingDto> getAllTrainingsByActivityType(@RequestParam String activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType);
    }

    @PutMapping("/trainings/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto updatedTraining) {
        return trainingProvider.updateTraining(trainingId, updatedTraining);
    }

}
