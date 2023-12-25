package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

//@Data
//@Entity
//@Table(name = "ad")
public class Ad {
  //  @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author;
 //   @Column
    private String image;
  //  @Column
    private Integer pk;
 //   @Column
    private Integer price;
  //  @Column
    private String title;
//    @Column
    private String description;


    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 64)
    private String description;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private Collection<Comment> comments;

    @OneToOne
    @JsonBackReference                  // чтобы не было цикличности в Json
    @JoinColumn(name = "image_id")
    private Image image;
    */
}
