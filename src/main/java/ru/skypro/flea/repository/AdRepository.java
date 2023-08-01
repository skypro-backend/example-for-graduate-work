package ru.skypro.flea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.flea.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
