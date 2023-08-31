package com.renan.Workshop_mongo.Controller;


import com.renan.Workshop_mongo.DTO.ComentDTO;
import com.renan.Workshop_mongo.Document.Post;
import com.renan.Workshop_mongo.Repository.PostRepository;
import com.renan.Workshop_mongo.Service.PostNotFoundException;
import com.renan.Workshop_mongo.Service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getUser() {
        List<Post> post = postService.getUser();
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Post> postUser(@PathVariable String email, @RequestBody Post post) {
        Post postnew = postService.createPost(email, post);
        return ResponseEntity.ok().body(postnew);
    }

    @PostMapping("/coment/{id}")
    public ResponseEntity<Post> makeComent(@PathVariable long id, @RequestBody ComentDTO coment) throws PostNotFoundException {
        Post comentPost = postService.makeComent(id, coment);
        return ResponseEntity.ok().body(comentPost);

    }

}
