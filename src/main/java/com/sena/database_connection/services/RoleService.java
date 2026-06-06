package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.repositories.RoleRepository;

@Service
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

    public Role crear(Role role){
        return this.repository.save(role);
    }

    public Role actualizar (Role role){
        Optional <Role> rolefound = this.porId(role.getId());
        if (rolefound.isEmpty()) {
            return null;
        }

        return this.repository.save(role);
    }

    public Role eliminar ( Long id){
        Optional<Role> roleFond = this.porId(id);
        if (roleFond.isEmpty()){
            return null;
        }
        this.repository.delete(roleFond.get());
        return roleFond.get();
    }   
}
