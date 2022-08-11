package ru.skypro.homework.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "ads_images")
public class Image implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Ads ads;
    private Long size;
    private String title;
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;

    public Image(String id, Ads ads, Long size, String title, byte[] context) {
        this.id = id;
        this.ads = ads;
        this.size = size;
        this.title = title;
        this.content = content;
    }

    public Image() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", ads=" + ads +
                ", size=" + size +
                ", title='" + title + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(ads, image.ads) && Objects.equals(size, image.size) && Objects.equals(title, image.title) && Arrays.equals(content, image.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, ads, size, title);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
