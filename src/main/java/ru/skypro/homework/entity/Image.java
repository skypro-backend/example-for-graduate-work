package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private long id;

    @Lob
    private byte[] bytes;

    @JoinColumn(name = "ad_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ad ad;

    @JoinColumn(name = "user_id")
    @OneToMany
    private User user;
}
