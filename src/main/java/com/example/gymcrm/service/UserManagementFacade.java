package com.example.gymcrm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementFacade {
    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
    private static final Logger logger = LoggerFactory.getLogger(UserManagementFacade.class);
    @Autowired
    public UserManagementFacade(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void doSomethingWithUsers() {
    }

    // Example method using traineeService
    public void doSomethingWithTrainees() {
    }

    // Example method using trainerService
    public void doSomethingWithTrainers() {
    }

    // Example method using trainingService
    public void doSomethingWithTraining() {
    }
}
