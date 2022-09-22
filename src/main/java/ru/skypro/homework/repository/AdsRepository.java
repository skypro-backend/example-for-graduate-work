package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.entity.Ads;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    Ads findAdsByPk(Integer pk);

}
