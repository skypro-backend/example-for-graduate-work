package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.projection.ExtendedAd;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    @Query("SELECT e FROM Ad e")
    List<Ad> findAllAds();
    @Query("")
    List<Ad> getAllAdsByUser(String user);
    @Query("")
    ExtendedAd findAllAdFullInfo(Integer id);
}
