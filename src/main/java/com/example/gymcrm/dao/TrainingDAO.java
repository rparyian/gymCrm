package com.example.gymcrm.dao;

import com.example.gymcrm.model.Training;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class TrainingDAO {
    private Map<Long, Training> trainingMap = new HashMap<>();
    private long trainingIdCounter = 1;

    public void addTraining(Training training) {
        training.setId(trainingIdCounter++);
        trainingMap.put(training.getId(), training);
    }

    public Training getTrainingById(long trainingId) {
        return trainingMap.get(trainingId);
    }

    public void updateTraining(Training training) {
        if (trainingMap.containsKey(training.getId())) {
            trainingMap.put(training.getId(), training);
        } else {
            // Handle error: Training not found
        }
    }

    public void deleteTraining(long trainingId) {
        trainingMap.remove(trainingId);
    }
}
