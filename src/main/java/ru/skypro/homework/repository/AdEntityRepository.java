package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;

public interface AdEntityRepository extends JpaRepository<AdEntity, Integer> {
}
