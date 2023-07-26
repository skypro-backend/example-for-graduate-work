package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Long> {
    Optional<Ad> findById(Long id);
}
