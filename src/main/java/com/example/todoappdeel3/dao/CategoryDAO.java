package com.example.todoappdeel3.dao;



import com.example.todoappdeel3.dto.CategoryDTO;
import com.example.todoappdeel3.models.Category;
import com.example.todoappdeel3.models.Product;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Component
public class CategoryDAO {

    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository; }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryDTO) {
        this.categoryRepository.save(new Category(categoryDTO.name));
    }


    public Category getCategory(long id){
        Optional<Category> category = this.categoryRepository.findById(id);

        if(category.isPresent()) {
            return category.get();
        }  else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "De gevraagde category is niet gevonden"
            );
        }

    }


    public void createCategory(@NotNull Category category){
        this.categoryRepository.save(category);
    }

}
