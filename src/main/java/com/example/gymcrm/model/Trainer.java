package com.example.gymcrm.model;

import lombok.Builder;
import lombok.Data;
@Data
public class Trainer {
    private static long trainerIdCounter = 1;

    private long id;
    private Specialization specialization;
    private long userId; // Foreign Key

    public Trainer(Specialization specialization, long userId) {
        this.specialization = specialization;
        this.userId = userId;
        this.id=generateTraineeId();
    }

    private synchronized long generateTraineeId() {
        return trainerIdCounter++;
    }
}
