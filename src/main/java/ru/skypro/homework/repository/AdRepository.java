package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.projection.ExtendedAd;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    @Query("SELECT e FROM Ad e")
    List<Ad> findAllAds();
    @Query("")
    List<Ad> getAllAdsByUser(String user);
    @Query("SELECT new ru.skypro.homework.projection.ExtendedAd (a.pk,u.firstName,u.lastName,u.lastName,u.email,a.image,u.phone,a.price,a.title) FROM Ad a JOIN User u ON u = a.user WHERE u.id = :id")
    Optional<ExtendedAd> findAllAdFullInfo(@Param("id")Integer id);
}