package com.example.todoappdeel3.dao;

import com.example.todoappdeel3.models.Options;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionsDAO {

    private final OptionsRepository optionsRepository;

    public OptionsDAO(OptionsRepository optionsRepository) {
        this.optionsRepository = optionsRepository;
    }

    public List<Options> getAllOptions(){
        return this.optionsRepository.findAll();
    }


}
