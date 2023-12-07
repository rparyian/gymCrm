package com.example.gymcrm.dao;

import com.example.gymcrm.Exceptions.UserNotFoundException;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class UserDAO {
    private Map<Long, User> userMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private long userIdCounter = 1;

    public void addUser(User user) {
        user.setId(userIdCounter++);
        if (nameAndLastNameAreNotUnique(user))
            generateUniqueUserName(user);
        userMap.put(user.getId(), user);
    }
    Boolean nameAndLastNameAreNotUnique(User newUser){
       return userMap.values().stream()
                .anyMatch(user -> user.getFirstName().equals(newUser.getFirstName()) &&
                        user.getLastName().equals(newUser.getLastName()));
    }
    void generateUniqueUserName(User user){
        String suffix=generateSuffix(user);
        String uniqueUserName = new StringBuffer().append(user.getFirstName())
                .append(".")
                .append(user.getLastName())
                .append(suffix)
                .toString();
        user.setUserName(uniqueUserName);
    }

    String generateSuffix(User comparedUser) {
       Long counter= userMap.values().stream()
                .filter(user ->
                        user.getFirstName().equals(comparedUser.getFirstName()) &&
                                user.getLastName().equals(comparedUser.getLastName()))
                .count();
       return counter++ + "";
    }

    public User getUserById (long userId) {
       User user =  userMap.get(userId);
        if (user == null) {
            logger.error("User with ID {} not found", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        return userMap.get(userId);
    }
    public User getUserByUserName(String userName) {
        for (User user : userMap.values()) {
            if (user.getUserName().equals(userName))
                return user;
        }
        logger.error("User with username {} not found", userName);
        throw new UserNotFoundException("User with username " + userName + " not found");
    }
    public void updateUser(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
        } else {
            logger.error("User with ID {} not found", user.getId());
            throw new UserNotFoundException("User with ID " + user.getId() + " not found");
            // Handle error: User not found
        }
    }

    public void deleteUser(long userId) {
        userMap.remove(userId);
    }

}
