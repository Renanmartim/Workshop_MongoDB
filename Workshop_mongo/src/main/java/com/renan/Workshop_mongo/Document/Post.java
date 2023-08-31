package com.renan.Workshop_mongo.Document;

import com.renan.Workshop_mongo.DTO.ComentDTO;
import com.renan.Workshop_mongo.DTO.UserDTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Post {

    @Id
    private ObjectId id;
    private Date date;

    private long idAsLong;
    private String title;
    private String body;

    private List<ComentDTO> coments = new ArrayList<>();

    public Post(){}

    public Post(Date date, String title, String body) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<ComentDTO> getComents() {
        return coments;
    }

    public void setComents(List<ComentDTO> coments) {
        this.coments = coments;
    }

    public long getIdAsLong() {
        return idAsLong;
    }

    public void setIdAsLong(long idAsLong) {
        this.idAsLong = idAsLong;
    }
}
