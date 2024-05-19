package com.example.todoappdeel3.controller;


import com.example.todoappdeel3.dao.UserDAO;
import com.example.todoappdeel3.models.CustomUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http:localhost:4200")
public class UserController {

    public UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PreAuthorize(" hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomUser>> getAllUsers() {
        return ResponseEntity.ok(userDAO.getAllUsers());
    }

}
