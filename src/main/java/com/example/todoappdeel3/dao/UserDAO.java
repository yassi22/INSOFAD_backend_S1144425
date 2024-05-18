package com.example.todoappdeel3.dao;

import com.example.todoappdeel3.models.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomUser getUser(long id){
        Optional<CustomUser> user = this.userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user has been found");
        }
    }

}
