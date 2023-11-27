package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.UpdateAd;

import java.util.Optional;

@Repository
public interface ExtendedAdRepository extends JpaRepository<ExtendedAd, Integer> {
    static Optional<Object> findByPk(Integer id) {
        return null;
    }

    Object getPk();
}
