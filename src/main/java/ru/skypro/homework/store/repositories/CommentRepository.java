package ru.skypro.homework.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.store.entities.CommentEntity;
import ru.skypro.homework.store.entities.ImageIEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
