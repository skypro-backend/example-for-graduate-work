package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<AdsEntity, Integer> {
    List<AdsEntity> findAdsEntityByAuthor_Id(Integer id);
}
