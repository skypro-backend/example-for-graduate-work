package ru.skypro.homework.repository;

import liquibase.pro.packaged.Q;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.projection.Ads;
import ru.skypro.homework.projection.ExtendedAd;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    @Query("SELECT count(a) FROM Ad a JOIN User u ON a.user = u AND u.email = :user")
    Long getCountOfAdsByUser(@Param("user") String user);
    @Query("SELECT a FROM Ad a JOIN User u ON a.user = u AND u.email = :user")
    List<Ad> getAllAdsByUser(@Param("user")String user);
    @Query("SELECT new ru.skypro.homework.projection.ExtendedAd" +
            " (a.pk," +
            "u.firstName," +
            "u.lastName," +
            "a.description," +
            "u.email," +
            "a.image," +
            "u.phone," +
            "a.price," +
            "a.title) FROM Ad a JOIN User u ON u = a.user WHERE u.id = :id")
    Optional<ExtendedAd> findAllAdFullInfo(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Ad SET image = :image")
    String updateAdImage(@Param("image") String image);
}