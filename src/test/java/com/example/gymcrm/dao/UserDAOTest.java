package com.example.gymcrm.dao;

import com.example.gymcrm.Exceptions.UserNotFoundException;
import com.example.gymcrm.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDAOTest {
    @Mock
    private Map<Long, User> userMap;

    @InjectMocks
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User("John", "Doe", true);

        when(userMap.containsValue(any(User.class))).thenReturn(false);

        userDAO.addUser(user);

        assertEquals(1, user.getId());
        verify(userMap, times(1)).put(eq(1L), eq(user));
    }

    @Test
    void testGetUserById() {
        long userId = 1;
        User expectedUser = new User("John", "Doe", true);

        when(userMap.get(eq(userId))).thenReturn(expectedUser);

        User actualUser = userDAO.getUserById(userId);

        assertEquals(expectedUser, actualUser);
    }
    @Test
    void testGetUserByUserName() {
        String userName = "john.doe";
        User expectedUser = new User("John", "Doe", true);
        expectedUser.setUserName(userName);

        when(userMap.values()).thenReturn(Collections.singletonList(expectedUser));

        User actualUser = userDAO.getUserByUserName(userName);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User("John", "Doe", true);
        existingUser.setId(1L);

        User updatedUser = new User("Updated", "User", false);
        updatedUser.setId(existingUser.getId());

        when(userMap.containsKey(eq(existingUser.getId()))).thenReturn(true);

        userDAO.updateUser(updatedUser);

        verify(userMap, times(1)).put(eq(existingUser.getId()), eq(updatedUser));
    }
    @Test
    void testDeleteUser() {
        long userId = 1L;

        userDAO.deleteUser(userId);

        verify(userMap, times(1)).remove(eq(userId));
    }
    @Test
    void testNameAndLastNameAreNotUnique() {
        // Prepare a user with the same first name and last name
        User existingUser = new User("John", "Doe", true);

        // Mock behavior to simulate existing user
        when(userMap.values()).thenReturn(List.of(existingUser));

        // Call the method with a user having the same first name and last name
        boolean result = userDAO.nameAndLastNameAreNotUnique(new User("John", "Doe", true));

        // Verify that the method returns true, indicating non-uniqueness
        assertTrue(result);
    }
    @Test
    void testGenerateSuffix() {
        // Prepare existing users with the same first name and last name
        User existingUser1 = new User("John", "Doe", true);
        User existingUser2 = new User("John", "Doe", true);

        // Mock behavior to simulate existing users
        when(userMap.values()).thenReturn(List.of(existingUser1, existingUser2));

        // Call the method to generate the suffix
        String suffix = userDAO.generateSuffix(new User("John", "Doe", true));

        // Verify that the method returns the correct suffix
        assertEquals("2", suffix); // Assuming the suffix starts from 1
    }
}

    // Add more tests for other methods as needed

