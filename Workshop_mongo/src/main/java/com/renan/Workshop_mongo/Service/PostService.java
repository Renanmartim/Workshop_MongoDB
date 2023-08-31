package com.renan.Workshop_mongo.Service;


import com.renan.Workshop_mongo.Component.MongoComponent;
import com.renan.Workshop_mongo.DTO.ComentDTO;
import com.renan.Workshop_mongo.DTO.UserDTO;
import com.renan.Workshop_mongo.Document.Post;
import com.renan.Workshop_mongo.Document.User;
import com.renan.Workshop_mongo.Repository.PostRepository;
import com.renan.Workshop_mongo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoComponent mongoComponent;

    public Post createPost(String email, Post post){
        User usersearch = userRepository.findAllByEmail(email);

        if (usersearch == null) {
            throw new ObjectNotFoundException("User with email " + email + " not found");
        }

        Date date = new Date();
        Post postnew = new Post(date, post.getBody(), post.getTitle());

        postnew = postRepository.insert(postnew);

        // Get the id as long after saving the post
        long postIdAsLong = mongoComponent.convert(postnew.getId());
        postnew.setIdAsLong(postIdAsLong);

        usersearch.getPosts().add(postnew);
        userRepository.save(usersearch);

        return postnew;
    }

    public List<Post> getUser() {
        List<Post> postUsers = postRepository.findAll();
        return postUsers;
    }

    public Post makeComent(long postId, ComentDTO commentDTO) throws PostNotFoundException {
        Optional<Post> optionalPost = Optional.ofNullable(postRepository.findByLongId(postId));

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Date currentDate = new Date();

            User nUser = userRepository.findRandomUser().getUniqueMappedResult();


            UserDTO userDTO = new UserDTO(nUser.getName(), nUser.getEmail());
            ComentDTO newComment = new ComentDTO(commentDTO.getText(), currentDate, userDTO);


            post.getComents().add(newComment);
            postRepository.save(post);

            return post;
        } else {
            throw new PostNotFoundException("Post with ID " + postId + " not found");
        }
    }
}
