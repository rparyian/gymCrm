package com.example.gymcrm.service;

import com.example.gymcrm.dao.TraineeDAO;
import com.example.gymcrm.dataInitialization.DataInitializer;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TraineeService implements GeneralService {
    private TraineeDAO traineeDAO;
    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);
    @Autowired
    public TraineeService(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    public void addTrainee(Date dateOfBirth, String address, User user) {
        Trainee trainee = new Trainee(dateOfBirth,address, user.getId());
        traineeDAO.addTrainee(trainee);
    }

    public Trainee getTraineeById(long traineeId) {
        return traineeDAO.getTraineeById(traineeId);
    }
    public Trainee getTraineeByUserId(long userId) {
        return traineeDAO.getTraineeByUserId(userId);
    }

    public void updateTrainee(Trainee trainee) {
        traineeDAO.updateTrainee(trainee);
    }

    public void deleteTrainee(long traineeId) {
        traineeDAO.deleteTrainee(traineeId);
    }
}
