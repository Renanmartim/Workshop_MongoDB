package com.renan.Workshop_mongo.Service;

import com.renan.Workshop_mongo.Component.MongoComponent;
import com.renan.Workshop_mongo.Document.User;
import com.renan.Workshop_mongo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoComponent mongoComponent;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getEmail(String email){
        User user = userRepository.findAllByEmail(email);

        if (user == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return user;
    }

    public User postUser(User user) {
        User newUser = userRepository.insert(user);
        newUser.setIdAsLong(mongoComponent.convert(newUser.getId())); // Assuming setIdAsLong sets the Long ID
        userRepository.save(newUser); // Save the updated user with the converted ID
        return newUser;
    }

    public User deleteUser(String email) {
        User user = userRepository.findAllByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        userRepository.delete(user);
        return user;
    }

    public User putUser(String email, User user) {
        User uservar = userRepository.findAllByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        uservar.setEmail(user.getEmail());
        uservar.setName(user.getName());
        uservar.setPassword(user.getPassword());
        uservar.setIdAsLong(mongoComponent.convertObjectIdToLong(user.getId()));
        userRepository.save(uservar);// Update the ID
        return uservar;

    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
