package com.renan.Workshop_mongo.Service;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String mensage){
        super(mensage);
    }
}
