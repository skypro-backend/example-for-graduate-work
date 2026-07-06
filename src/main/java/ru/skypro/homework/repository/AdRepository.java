package ru.skypro.homework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;

public interface AdRepository extends JpaRepository<AdEntity, Long> {

    Page<AdEntity> findByAuthorId(long userId, Pageable pageable);
}