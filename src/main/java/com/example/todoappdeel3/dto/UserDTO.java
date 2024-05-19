package com.example.todoappdeel3.dto;

public class UserDTO {

    public String firstname;

    public String lastname;

    public String email;

    public String password;

    public String role;

    public UserDTO(String firstname, String lastname, String email, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
