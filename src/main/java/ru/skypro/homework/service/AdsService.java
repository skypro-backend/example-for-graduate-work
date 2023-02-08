package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;

import java.util.Collection;


public interface AdsService {

    Collection<Comment> getAdsComments (Integer pk);


    void addAdsComments (Integer pk);


    void deleteAdsComment (Integer pk, Integer id);



}
