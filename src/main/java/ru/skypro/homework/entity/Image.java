package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private long size;
@Column(name = "contentType")
    private String contentType;
@Column(name = "isPreviewImage")
    private boolean isPreviewImage;
    @Lob
    private byte[] bytes;
    @JoinColumn(name = "ad_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ad ad;
}
