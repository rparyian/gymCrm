package com.example.gymcrm.model;

import com.example.gymcrm.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@Data
public class User {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static long userIdCounter = 1;
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean isActive;
    public User(String firstName, String lastName, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = generateUserName(firstName,lastName);
        this.password = generateRandomPassword();
        this.isActive = isActive;
        this.id = generateUserId();
    }

    private static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
    private String generateUserName(String firstName, String lastName){
        String userName = new StringBuffer().append(firstName)
                .append(".")
                .append(lastName)
                .toString();

        return userName;
    }

    private synchronized long generateUserId() {
        return userIdCounter++;
    }
}
