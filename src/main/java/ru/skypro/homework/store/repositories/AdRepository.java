package ru.skypro.homework.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.store.entities.AdEntity;
import ru.skypro.homework.store.entities.CommentEntity;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
}
