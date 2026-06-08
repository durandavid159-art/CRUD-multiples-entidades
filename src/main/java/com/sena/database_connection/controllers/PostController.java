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

import com.sena.database_connection.dtos.PostDto;
import com.sena.database_connection.entities.Post;
import com.sena.database_connection.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    
    public PostService service;

    public PostController (PostService service){
        this.service = service;
    }

    @GetMapping
    public List <Post> get(){
        return this.service.obetenerTodos();
    }

    @GetMapping ("/{id}")
    public ResponseEntity <Post> getById(@PathVariable ("id") Long id){
        Optional <Post> post=this.service.porid(id);

        if (post.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(post.get());
    }

    @PostMapping
    public ResponseEntity <Post>create (@RequestBody PostDto body){
        Post post = new Post();

        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setLikes(body.getLikes());
        post.setUser(body.getUser());

        Post postCreado = this.service.crear(post);

        return ResponseEntity.status(201).body(postCreado);
    }

    @PutMapping ("/{id}")
    public ResponseEntity <Post> update (@PathVariable ("id")Long id, @RequestBody PostDto body){
        
        Post post = new Post();

        post.setId(id);
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setLikes(body.getLikes());
        post.setUser(body.getUser());

        Post postUpdate = this.service.actualizar(post);

        if (postUpdate == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Post> delete (@PathVariable ("id") Long id){

        Post posrEliminado = this.service.eliminar(id);

        if (posrEliminado == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(posrEliminado);
    }

}
