package com.example.gymcrm.service;

import com.example.gymcrm.dao.TrainingDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TrainingService implements GeneralService{
    private TrainingDAO trainingDAO;
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);
    @Autowired
    public TrainingService(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    public Training addTraining(Trainee trainee, Trainer trainer, String trainingName, Date trainingDate, int trainingDuration) {
        Training training =new Training(trainee.getId(),trainer.getId(),trainingName,
                trainer.getSpecialization().ordinal(),trainingDate,trainingDuration);
                trainingDAO.addTraining(training);
                return training;
    }

    public Training getTrainingById(long trainingId) {
        return trainingDAO.getTrainingById(trainingId);
    }

    public void updateTraining(Training training) {
        trainingDAO.updateTraining(training);
    }

    public void deleteTraining(long trainingId) {
        trainingDAO.deleteTraining(trainingId);
    }
}
