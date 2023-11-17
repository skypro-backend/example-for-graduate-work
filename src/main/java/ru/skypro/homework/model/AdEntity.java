package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Comment;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ads")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String description;

    @OneToOne
    private PhotoEntity photo;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToMany(mappedBy = "ad")
    private Collection<Comment> comments;
}
