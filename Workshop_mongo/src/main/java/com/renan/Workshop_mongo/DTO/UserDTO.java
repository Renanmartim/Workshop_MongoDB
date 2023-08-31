package com.renan.Workshop_mongo.DTO;

import com.renan.Workshop_mongo.Document.Post;
import com.renan.Workshop_mongo.Document.User;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long idAsLong;
    private String name;
    private String email;

    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    public UserDTO(String marcio, String mail) {}

    public UserDTO(long idAsLong, String name, String email) {
        this.idAsLong = idAsLong;
        this.name = name;
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getIdAsLong() {
        return idAsLong;
    }

    public void setIdAsLong(long idAsLong) {
        this.idAsLong = idAsLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Static method to convert a User to a UserDTO
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getIdAsLong(), user.getName(), user.getEmail());
    }
}
