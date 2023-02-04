package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.entity.Ads;

public interface AdsRepository extends JpaRepository<Ads, Long> {

}