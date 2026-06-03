package com.sena.database_connection.services;

import com.sena.database_connection.repositories.RoleRepository;


public class RoleService {
    private RoleRepository repository;

    public RoleService (RoleRepository repository){
        this.repository = repository;
    }
}
