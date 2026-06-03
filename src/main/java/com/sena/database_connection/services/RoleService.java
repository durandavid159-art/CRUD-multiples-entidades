package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.repositories.RoleRepository;


public class RoleService {

    private RoleRepository repository;

    public RoleService (RoleRepository repository){
        this.repository = repository;
    }

    public List <Role> obtenerTodos (){
        return this.repository.findAll();
    }

    public Optional <Role> porId (Long id){
        return this.repository.findById(id);
    }
}
