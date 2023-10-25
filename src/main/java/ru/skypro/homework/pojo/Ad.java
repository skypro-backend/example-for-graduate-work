package ru.skypro.homework.pojo;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data

public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Long pk;
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;
    @Column(name = "title")
    private String title;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable=false, updatable=false)
    private Long UserID;

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "image_id", insertable=false, updatable=false)
    private Image image;

    @Column(name = "image_id")

    private Long imageId;

}
