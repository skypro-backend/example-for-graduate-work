package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.util.List;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    List<AdEntity> findByAuthor(UserEntity author);
}
