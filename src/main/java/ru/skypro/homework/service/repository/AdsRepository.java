package ru.skypro.homework.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad, Integer> {

    Ad findById(int adsId);
    List<Ad> findAdByUser(User user);
    List<Ad> findAdsByTitleContaining(String req);

}
