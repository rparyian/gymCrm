package com.example.gymcrm.dao;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class TraineeDAO {
    private Map<Long, Trainee> traineeMap = new HashMap<>();
    private long traineeIdCounter = 1;

    public void addTrainee(Trainee trainee) {
        trainee.setId(traineeIdCounter++);
        traineeMap.put(trainee.getId(), trainee);
    }

    public Trainee getTraineeById(long traineeId) {
        return traineeMap.get(traineeId);
    }

    public Trainee getTraineeByUserId(long userId) {
        for (Trainee trainee : traineeMap.values()) {
            if (trainee.getUserId()==userId)
                return trainee;
        }
        return null;
    }
    public void updateTrainee(Trainee trainee) {
        if (traineeMap.containsKey(trainee.getId())) {
            traineeMap.put(trainee.getId(), trainee);
        } else {
            // Handle error: Trainee not found
        }
    }

    public void deleteTrainee(long traineeId) {
        traineeMap.remove(traineeId);
    }
}
