package com.example.todoappdeel3;

import com.example.todoappdeel3.controller.UserController;
import com.example.todoappdeel3.dao.UserDAO;
import com.example.todoappdeel3.dto.UserDTO;
import com.example.todoappdeel3.models.CustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<CustomUser> users = new ArrayList<>();
        users.add(new CustomUser());
        users.add(new CustomUser());

        when(userDAO.getAllUsers()).thenReturn(users);

        ResponseEntity<List<CustomUser>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(userDAO, times(1)).getAllUsers();
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO("John", "Doe", "john.doe@example.com", "password123", "ROLE_USER");
        doNothing().when(userDAO).createUser(userDTO);

        ResponseEntity<String> response = userController.createUser(userDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Created a new user", response.getBody());
        verify(userDAO, times(1)).createUser(userDTO);
    }

    @Test
    public void testCreateUser_Exception() {
        UserDTO userDTO = new UserDTO("Jane", "Doe", "jane.doe@example.com", "password123", "ROLE_USER");
        doThrow(new RuntimeException()).when(userDAO).createUser(userDTO);

        ResponseEntity<String> response = userController.createUser(userDTO);

        assertEquals(500, response.getStatusCodeValue());
        verify(userDAO, times(1)).createUser(userDTO);
    }
}
