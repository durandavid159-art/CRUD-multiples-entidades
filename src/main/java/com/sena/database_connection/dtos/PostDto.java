package com.sena.database_connection.dtos;

import com.sena.database_connection.entities.User;

import lombok.Data;

@Data   
public class PostDto {
    private String title;
    private String description;
    private Integer likes;
    private User user;
}
