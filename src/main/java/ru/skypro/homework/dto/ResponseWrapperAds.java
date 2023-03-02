package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAds {
    Integer count;
    // пока коллекцию поставила, вроде там не понятно сколько будет объявлений
    Collection<Ads> results;
}
