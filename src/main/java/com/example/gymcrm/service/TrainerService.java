package com.example.gymcrm.service;

import com.example.gymcrm.dao.TrainerDAO;
import com.example.gymcrm.dataInitialization.DataInitializer;
import com.example.gymcrm.model.Specialization;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class TrainerService implements GeneralService {
    private TrainerDAO trainerDAO;
    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);
    @Autowired
    public TrainerService(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    public void addTrainer(String specialization, User user) {
        Trainer trainer = new Trainer(Specialization.valueOf(specialization),user.getId());
        trainerDAO.addTrainer(trainer);
    }

    public Trainer getTrainerById(long trainerId) {
        return trainerDAO.getTrainerById(trainerId);
    }

    public Trainer getTrainerByUserId(long userId) {
        return trainerDAO.getTrainerByUserId(userId);
    }
    public void updateTrainer(Trainer trainer) {
        trainerDAO.updateTrainer(trainer);
    }

    public void deleteTrainer(long trainerId) {
        trainerDAO.deleteTrainer(trainerId);
    }
}
