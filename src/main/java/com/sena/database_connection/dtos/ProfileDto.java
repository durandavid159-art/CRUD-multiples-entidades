package com.sena.database_connection.dtos;

import com.sena.database_connection.entities.User;

import lombok.Data;

@Data
public class ProfileDto {
    private String username;
    private String description;
    private User user;
}
