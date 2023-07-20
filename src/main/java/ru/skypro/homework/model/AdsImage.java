package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Ad;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads_images")
public class AdsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adsImageId;

    @OneToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    private String imageAddress;
}
