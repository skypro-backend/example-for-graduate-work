package ru.skypro.flea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.flea.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
