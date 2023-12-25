package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class CommentEntity {
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Long createdAt;
    @Getter
    private String text;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;
}
