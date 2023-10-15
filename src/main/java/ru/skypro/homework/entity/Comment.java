package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность комментария
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comments")
public class Comment {
    /**
     * id  комментария
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    /**
     * Id автора комментария
     */

     @Column(name = "author_id")
     private Integer author;
     /**
      * ссылка на  автор автора(пользователя) комментария
      */
    @Column(name = "comments_image")
    private String authorImage;
    /**
     * имя автора комментария
     */
    @Column(name="first_name_author")
    private String authorFirstName;
    /**
     * Дата и время комментария
     */
    @Column(name="create_ad")
    private Long createdAt;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String test;

   // @ManyToOne
   // private Ad ads;

  }
