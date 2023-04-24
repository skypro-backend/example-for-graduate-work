package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
@Entity
public class Avatar {
    @Id
    String id;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;
}
