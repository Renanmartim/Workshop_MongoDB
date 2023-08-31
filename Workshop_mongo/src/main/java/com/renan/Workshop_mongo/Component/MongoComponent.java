package com.renan.Workshop_mongo.Component;

import com.renan.Workshop_mongo.Document.User;
import org.springframework.core.convert.converter.Converter;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoComponent implements Converter<ObjectId, Long> {

    @Override
    public Long convert(ObjectId objectId) {
        return convertObjectIdToLong(objectId);
    }

    public long convertObjectIdToLong(ObjectId objectId) {
        byte[] bytes = objectId.toByteArray();
        long longValue = 0;
        for (int i = 0; i < Math.min(bytes.length, 8); i++) {
            longValue = (longValue << 8) | (bytes[i] & 0xff);
        }
        return longValue;
    }
}
