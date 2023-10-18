package ru.skypro.homework.entity;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.Lob;

@Entity
public class Image {
    @Id
    private String id;

    @Lob
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setId(String string) {
    }

    public void setImage(byte[] bytes) {
    }

    public void getId() {
    }


}


