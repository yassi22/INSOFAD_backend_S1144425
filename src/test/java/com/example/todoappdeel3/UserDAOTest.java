package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.UserDAO;
import com.example.todoappdeel3.dto.UserDTO;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.dao.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<CustomUser> users = new ArrayList<>();
        users.add(new CustomUser("John", "Doe", "john.doe@example.com", "password123", "ROLE_USER"));
        users.add(new CustomUser("Jane", "Doe", "jane.doe@example.com", "password123", "ROLE_USER"));

        when(userRepository.findAll()).thenReturn(users);

        List<CustomUser> result = userDAO.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUser_Success() {
        CustomUser user = new CustomUser("John", "Doe", "john.doe@example.com", "password123", "ROLE_USER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        CustomUser result = userDAO.getUser(1L);

        assertEquals("John", result.getFirstname());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUser_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userDAO.getUser(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO("John", "Doe", "john.doe@example.com", "PasswordWordPass24241!", "ROLE_USER");
        CustomUser user = new CustomUser(userDTO.firstname, userDTO.lastname, userDTO.email, userDTO.password, "ROLE_USER");

        doNothing().when(userRepository).save(user);

        userDAO.createUser(userDTO);

        verify(userRepository, times(1)).save(any(CustomUser.class));
    }
}
