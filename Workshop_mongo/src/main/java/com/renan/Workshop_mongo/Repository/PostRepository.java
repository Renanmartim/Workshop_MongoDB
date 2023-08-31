package com.renan.Workshop_mongo.Repository;

import com.renan.Workshop_mongo.Document.Post;
import com.renan.Workshop_mongo.Document.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {

    @Query("{ 'idAsLong' : ?0 }")
    Post findByLongId(Long idAsLong);
}
