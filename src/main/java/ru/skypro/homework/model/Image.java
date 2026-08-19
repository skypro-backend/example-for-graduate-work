package ru.skypro.homework.model;
import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    private String id;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;
}
