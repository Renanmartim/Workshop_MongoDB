package com.renan.Workshop_mongo.Repository;

import com.renan.Workshop_mongo.Document.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findAllByEmail(String email);

    @Aggregation(pipeline = {
            "{$sample: {size: 1}}"
    })
    AggregationResults<User> findRandomUser();
}
