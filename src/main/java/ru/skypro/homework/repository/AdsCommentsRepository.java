package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.entity.Comments;

@Repository
public interface AdsCommentsRepository extends JpaRepository<Comments, Integer> {
}
