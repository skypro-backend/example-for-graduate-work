package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.CommentDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;
    private String title;
    private Integer price;
    private String description;

    public Comment(Integer author, String authorImage, String authorFirstName, Long createdAt, Integer pk, String text, String title, Integer price, String description) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Comment(CommentDto commentDto) {
        this.author = commentDto.getAuthor();
        this.authorImage = commentDto.getAuthorImage();
        this.authorFirstName = commentDto.getAuthorFirstName();
        this.createdAt = commentDto.getCreatedAt();
        this.pk = commentDto.getPk();
        this.text = commentDto.getText();

    }

}
