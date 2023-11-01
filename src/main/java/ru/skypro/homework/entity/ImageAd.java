package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the class-entity for saving pictures from ads
 * @author Sulaeva Marina
 */
@Entity
@Table(name = "image_ad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageAd {
    @Id
    private String id;

    private byte [] image;
}
