package com.example.gymcrm.service;

import com.example.gymcrm.dao.UserDAO;
import com.example.gymcrm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class UserService implements GeneralService {
    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User addUser(String firstName, String lastName, boolean isActive) {
        User user = new User(firstName,lastName,isActive);
        userDAO.addUser(user);
        return user;
    }

    public User getUserById(long userId) {
        return userDAO.getUserById(userId);
    }
    public User getUserByUserName (String userName) {
        return userDAO.getUserByUserName(userName);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(long userId) {
        userDAO.deleteUser(userId);
    }
}
