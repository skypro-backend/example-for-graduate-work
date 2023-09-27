package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ad;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    Optional<Ad> findByPk(Integer pk);
    Optional<List<Ad>> findAdsByUser_UsernameContains(String userName);
}
