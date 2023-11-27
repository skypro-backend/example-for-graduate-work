package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Ads;


import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    static Optional<Object> findByPk(Integer id) {
        return null;
    }

    Object getPk();
}
