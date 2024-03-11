package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    @Query(value = "select ad_entity.* from ad_entity join user_entity on ad_entity.author_id=user_entity.pk where email = ?", nativeQuery = true)
    List<AdEntity> findAdsByEmail(String email);
}