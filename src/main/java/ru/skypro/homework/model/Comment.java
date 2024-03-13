package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель для комментариев к объявлениям
 * связанные таблицы UserDto (много комментов к одному UserDto)
 * и лист с комментариями (много комментов у одного AdsDto)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENT_", indexes = {
        @Index(name = "IDX_COMMENT_ADS", columnList = "ADS_ID"),
        @Index(name = "IDX_COMMENT_USER", columnList = "USER_ID")})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "TEXT")
    private String text;

    @JoinColumn(name = "ADS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ads;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
