package ru.skypro.homework.model;

import ru.skypro.homework.dto.CommentDto;

/*@Data
@Entity
@Table(name = "comments")*/
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

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = createdAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    /*

    ===================
    Скорее всего будет эта модель
    ===================

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
