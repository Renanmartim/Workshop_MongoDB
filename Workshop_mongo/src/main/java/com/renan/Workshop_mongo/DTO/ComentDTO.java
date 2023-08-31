package com.renan.Workshop_mongo.DTO;

import java.util.Date;

public class ComentDTO {

    private String text;
    private Date date;

    private UserDTO authorDto;

    public ComentDTO(String text, Date date, UserDTO authorDto) {
        this.text = text;
        this.date = date;
        this.authorDto = authorDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(UserDTO authorDto) {
        this.authorDto = authorDto;
    }
}
