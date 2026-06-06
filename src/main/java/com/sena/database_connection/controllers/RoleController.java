package com.sena.database_connection.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sena.database_connection.dtos.RoleDto;
import com.sena.database_connection.entities.Role;
import com.sena.database_connection.services.RoleService;

public class RoleController {
    private RoleService service;

    public RoleController (RoleService service) {
        this.service = service;
    }

    public List<Role> get(){
        return this.service.obtenerTodos();
    }

    public ResponseEntity<Role> create(RoleDto body){
        Role role = new Role();
        role.setName(body.getName());

        Role roleCreado = this.service.crear(role);
        return ResponseEntity.status(201).body(roleCreado);
    }

    public ResponseEntity<Role> delete (Long id){
        Role roleEliminado = this.service.eliminar(id);
        if (roleEliminado == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(roleEliminado);
    }
}
