package com.example.todoappdeel3.controller;


import com.example.todoappdeel3.dao.CategoryDAO;
import com.example.todoappdeel3.dto.CategoryDTO;
import com.example.todoappdeel3.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryDAO categoryDAO;


    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(this.categoryDAO.getAllCategories());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO){
        this.categoryDAO.createCategory(categoryDTO);
        return ResponseEntity.ok("Created a new category named " + categoryDTO.name);
    }
}
