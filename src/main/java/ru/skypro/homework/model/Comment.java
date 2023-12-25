package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

//@Data
//@Entity
//@Table(name = "comments")
public class Comment {
   // @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author;
 //   @Column
    private String authorImage;
 //   @Column
    private String authorFirstName;
  //  @Column
    private Long createdAt;
 //   @Column
    private Integer pk;
  //  @Column
    private String text;
  //  @Column
    private String title;
  //  @Column
    private Integer price;
  //  @Column
    private String description;

    /*
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false, length = 64)
    private String text;

    @Column(nullable = false)
    private Long createdAt;                      // Дата и время создания в миллисекундах с 01.01.1970

    */
}
