package com.example.gymcrm.dataInitialization;

import com.example.gymcrm.service.TraineeService;
import com.example.gymcrm.service.TrainerService;
import com.example.gymcrm.service.TrainingService;
import com.example.gymcrm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInitializer implements InitializingBean {
    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
    @Value("${data.file.path}")
    private String csvFilePath;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    public DataInitializer(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try (BufferedReader br = Files.newBufferedReader( Paths.get("src/main/resources/"+csvFilePath))) {
            String line;
            String section = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }
                if (line.startsWith("User data")) {
                    section = "User";
                } else if (line.startsWith("Trainee data")) {
                    section = "Trainee";
                } else if (line.startsWith("Trainer data")) {
                    section = "Trainer";
                } else if (line.startsWith("Training data")) {
                    section = "Training";
                } else {
                    String[] data = line.split(",");
                    switch (section) {
                        case "User":
                            String firstName = data[0];
                            String lastName = data[1];
                            Boolean isActive = Boolean.parseBoolean(data[2]);
                            logger.info("creating user" );
                            logger.info("user created "+userService.addUser(firstName,lastName,isActive));
                            break;
                        case "Trainee":
                            Date dateOfBirth = dateFormat.parse(data[0]);
                            String address = data[1];
                            long traineeUserId = Long.parseLong(data[2]);
                            logger.info("creating trainee");
                            traineeService.addTrainee(dateOfBirth,address,userService.getUserById(traineeUserId));
                            logger.info("trainee created" + traineeService.getTraineeByUserId(traineeUserId));
                            break;
                        case "Trainer":
                            String specialization = data[0].toUpperCase().replace(" ","_");
                            long trainerUserId = Long.parseLong(data[1]);
                            logger.info("creating trainer");
                            trainerService.addTrainer(specialization,userService.getUserById(trainerUserId));
                            logger.info("trainer created" + trainerService.getTrainerByUserId(trainerUserId));
                            break;
                        case "Training":
                            long traineeId = Long.parseLong(data[0]);
                            long trainerId = Long.parseLong(data[1]);
                            String trainingName = data[2];
                            int trainingType = Integer.parseInt(data[3]);
                            Date trainingDate = dateFormat.parse(data[4]);
                            int trainingDuration = Integer.parseInt(data[5]);
                            logger.info("creating training");

                            logger.info("training created "+
                                    trainingService.addTraining(traineeService.getTraineeById(traineeId),
                                            trainerService.getTrainerById(trainerId),trainingName,trainingDate,trainingDuration));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
