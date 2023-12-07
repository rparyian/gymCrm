package com.example.gymcrm.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
public class Training {
    private static long trainingIdCounter = 1;

    private long id;
    private long traineeId; // Foreign Key
    private long trainerId; // Foreign Key
    private String trainingName;
    private long trainingTypeId; // Foreign Key (connect with Specialization)
    private Date trainingDate;
    private Number trainingDuration;

    public Training(long traineeId, long trainerId, String trainingName, long trainingTypeId, Date trainingDate, Number trainingDuration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingName = trainingName;
        this.trainingTypeId = trainingTypeId;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
        this.id=generateTraineeId();
    }

    private synchronized long generateTraineeId() {
        return trainingIdCounter++;
    }

}
