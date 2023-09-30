package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

@Repository
public interface AdRepository extends CrudRepository<AdEntity, Integer> {
    List<AdEntity> findAllByAuthor(UserEntity author);
}
