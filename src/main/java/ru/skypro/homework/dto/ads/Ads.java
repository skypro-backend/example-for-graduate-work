package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.dto.ads.Ad;

import java.util.List;

@Data
public class Ads {

    private int count;
    private List<Ads> results;


}
