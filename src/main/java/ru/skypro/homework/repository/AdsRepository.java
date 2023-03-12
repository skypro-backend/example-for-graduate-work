package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

/**
 * репозиторий для объявления
 */

@Repository
public interface AdsRepository extends JpaRepository<AdEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT MAX(ID) FROM ads")
    int findMaxID();

}
