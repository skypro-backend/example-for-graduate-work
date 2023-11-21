package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.Ads;

public interface AdsRepository extends JpaRepository<Ads, Long> {
}
