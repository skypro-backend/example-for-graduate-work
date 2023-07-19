package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "ads")
<<<<<<< HEAD

=======
>>>>>>> origin/vik_branch_rep
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "imageAddress")
    private String imageAddress;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ads_id")
    private List<Comment> comments = new ArrayList<>();

    public Ad(Integer pk, User user, String imageAddress, String description, Integer price, String title, List<Comment> comments) {
        this.pk = pk;
        this.user = user;
        this.imageAddress = imageAddress;
        this.description = description;
        this.price = price;
        this.title = title;
        this.comments = comments;
    }

    public Ad() {
            }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

