package ru.skypro.homework.repository.ads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.ads.Ad;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
}
