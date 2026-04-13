package ru.skypro.homework.repository;

import ru.skypro.homework.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    List<AdEntity> findByAuthorId(Integer authorId);
    List<AdEntity> findByAuthorEmail(String email);
}