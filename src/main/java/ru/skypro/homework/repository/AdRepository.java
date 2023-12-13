package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    @Query(value = "select ad_entity.* from ad_entity where ad_entity.pk = ?", nativeQuery = true)
    List<AdEntity> findAdsByAuthor_id(String email);
}