package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {


    List<AdEntity> findByAuthorEmailIgnoreCase(String email);
    List<AdEntity> findAllByOrderByCreatedAtDesc();
}