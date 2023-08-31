package com.renan.Workshop_mongo.Controller;

import com.renan.Workshop_mongo.Component.MongoComponent;
import com.renan.Workshop_mongo.DTO.UserDTO;
import com.renan.Workshop_mongo.Document.Post;
import com.renan.Workshop_mongo.Document.User;
import com.renan.Workshop_mongo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoComponent mongoComponent;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDTO> userDTO = users.stream()
                .map(x -> new UserDTO(x.getIdAsLong(), x.getName(), x.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getEmailUser(@PathVariable String email){
        User user = userService.getEmail(email);
        return ResponseEntity.ok().body(new UserDTO(user.getIdAsLong(), user.getName(), user.getEmail()));
    }

    @GetMapping("/{email}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String email){
        User user = userService.getEmail(email);
        return ResponseEntity.ok().body(user.getPosts());
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody User user) {
        User savedUser = userService.postUser(user);
        return ResponseEntity.ok().body(new UserDTO(savedUser.getIdAsLong(), savedUser.getName(), savedUser.getEmail()));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String email) {
        User deletedUser = userService.deleteUser(email);
        return ResponseEntity.ok().body(new UserDTO(deletedUser.getIdAsLong(), deletedUser.getName(), deletedUser.getEmail()));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> putUser(@PathVariable String email, @RequestBody User user) {
        User updatedUser = userService.putUser(email, user);
        return ResponseEntity.ok().body(new UserDTO(updatedUser.getIdAsLong(), updatedUser.getName(), updatedUser.getEmail()));
    }

    @DeleteMapping("/deletAll")
    public void deleteAll(){
        userService.deleteAll();
    }
}
