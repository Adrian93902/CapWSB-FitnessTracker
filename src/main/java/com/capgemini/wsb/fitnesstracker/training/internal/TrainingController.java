package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/training")
public class TrainingController {
    private final TrainingProvider trainingProvider;
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserRepository userRepository;
    @GetMapping("/allTrainings")
    public List<TrainingDto> getAllTrainings(){
            return trainingService.getAllTrainings().stream().map(trainingMapper::toDto).toList();
    }
    @PostMapping("/createTraining")
    public Training createTraining(@RequestBody TrainingDto createTraining) {
        return trainingService.createTraining(createTraining);
    }
    @GetMapping("/getTraining/{trainingId}")
    public TrainingDto getTraining(@PathVariable Long trainingId) {
        TrainingDto training = trainingService.getTraining(trainingId);
        if (training == null) {
            throw new NotFoundException("Training not found with id: " + trainingId);
        }
        return training;
    }
    @GetMapping("/findTrainingByUser/{userId}")
    public List<TrainingDto> getAllTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getAllTrainingsForUser(userId);
    }

    //data do poprawy z godzinami
    @GetMapping("/getAllFinishedTrainingsByDate/{endTimeTraining}/user/{userId}")
    public List<TrainingDto> getAllFinishedTrainingsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endTimeTraining, @PathVariable Long userId) {
        System.out.println( endTimeTraining+" " + " "+userId );
        return trainingService.getAllFinishedTrainingsByDate(endTimeTraining, userId);
    }

    @GetMapping("/getAllTrainingsByActivityType/{activityType}/user/{userId}")
    public List<TrainingDto> getAllTrainingsByActivityType(@PathVariable String activityType, @PathVariable Long userId) {
        return trainingService.getAllTrainingsByActivityType(activityType, userId);
    }

    @PutMapping("/updateTraining/{trainingId}/user/{userId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @PathVariable Long userId, @RequestBody TrainingDto updatedTraining) {
        return trainingProvider.updateTraining(trainingId, userId, updatedTraining);
    }

}
