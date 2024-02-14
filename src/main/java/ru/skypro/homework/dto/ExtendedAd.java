package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import javax.persistence.Transient;

@Data
public class ExtendedAd extends Ad {
    @Transient
    private String authorFirstName;
    @Transient
    private String authorLastName;
    @Transient
    private Integer commentCount;
}
