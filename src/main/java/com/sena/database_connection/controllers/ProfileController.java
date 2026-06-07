package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.database_connection.dtos.ProfileDto;
import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.services.ProfileService;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    private ProfileService service;

    public ProfileController (ProfileService service){
        this.service = service;
    }

    @GetMapping
    public List<Profile> get(){
        return this.service.obetenerTodos();
    }

    @GetMapping ("/{id}")
    public ResponseEntity <Profile> getById(@PathVariable("id") long id){
        Optional <Profile> profile= this.service.porId(id);

        if (profile.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(profile.get());
    }

    @PostMapping
    public ResponseEntity <Profile> create(@RequestBody ProfileDto body){

        Profile profile = new Profile();

        profile.setUsername(body.getUsername());
        profile.setDescription(body.getDescription());
        profile.setUser(body.getUser());

        Profile profileCreado = this.service.crear(profile);

        return ResponseEntity.status(201).body(profileCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Profile> update (@PathVariable ("id") Long id, @RequestBody ProfileDto body){
        
        Profile profile = new Profile();
        
        profile.setId(id);
        profile.setUsername(body.getUsername());
        profile.setDescription(body.getDescription());
        profile.setUser(body.getUser());

        Profile profileUpdate = this.service.actualizar(profile);

        if (profileUpdate == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <Profile> delete (@PathVariable("id") Long id){

        Profile profilEliminado = this.service.eliminar(id);

        if (profilEliminado ==null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(profilEliminado);
    }
}
