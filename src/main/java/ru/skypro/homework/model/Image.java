package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    private String id;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "ad_id")
    private Ad ad;
    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Lob
    private byte[] image;
}
