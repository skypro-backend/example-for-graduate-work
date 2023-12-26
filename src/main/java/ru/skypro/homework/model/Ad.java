package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UserDto;

import javax.persistence.*;

/*@Data
@Entity
@Table(name = "ad")*/
public class Ad {
    //  @Id
    //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author;
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

 /*   public Ad(AdDto adDto) {

    }*/

    public Ad(Long author, String image, Integer pk, Integer price, String title, String description) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Ad(AdDto adDto) {
        this.author = adDto.getAuthor();
        this.image = adDto.getImage();
        this.pk = adDto.getPk();
        this.price = adDto.getPrice();
        this.title = adDto.getTitle();
    }

  /*
    ===================
    Скорее всего будет эта модель
    ===================

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
