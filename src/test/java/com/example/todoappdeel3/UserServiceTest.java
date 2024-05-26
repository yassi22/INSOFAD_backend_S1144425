package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.UserRepository;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        // Arrange
        CustomUser customUser = new CustomUser();
        customUser.setEmail("test@example.com");
        customUser.setPassword("password");
        customUser.setRole("ROLE_USER");
        when(userRepository.findByEmail("test@example.com")).thenReturn(customUser);

        // Act
        UserDetails result = userService.loadUserByUsername("test@example.com");

        // Assert
        UserDetails expectedUser = new User(
                "test@example.com",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        assertThat(result, is(expectedUser));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent@example.com");
        });
    }
}
