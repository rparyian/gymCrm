package com.example.gymcrm.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
public class Trainee {
    private static long traineeIdCounter = 1;

    private long id;
    private Date dateOfBirth;
    private String address;
    private long userId; // Foreign Key

    public Trainee(Date dateOfBirth, String address, long userId) {
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
        this.id=generateTraineeId();
    }

    private synchronized long generateTraineeId() {
        return traineeIdCounter++;
    }

}
