package com.example.gymcrm.dao;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class TrainerDAO {
    private Map<Long, Trainer> trainerMap = new HashMap<>();
    private long trainerIdCounter = 1;

    public void addTrainer(Trainer trainer) {
        trainer.setId(trainerIdCounter++);
        trainerMap.put(trainer.getId(), trainer);
    }

    public Trainer getTrainerById(long trainerId) {
        return trainerMap.get(trainerId);
    }

    public Trainer getTrainerByUserId(long userId) {
        for (Trainer trainer : trainerMap.values()) {
            if (trainer.getUserId()==userId)
                return trainer;
        }
        return null;
    }
    public void updateTrainer(Trainer trainer) {
        if (trainerMap.containsKey(trainer.getId())) {
            trainerMap.put(trainer.getId(), trainer);
        } else {
            // Handle error: Trainer not found
        }
    }

    public void deleteTrainer(long trainerId) {
        trainerMap.remove(trainerId);
    }
}
