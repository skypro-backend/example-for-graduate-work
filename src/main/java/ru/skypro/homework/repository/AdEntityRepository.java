package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

public interface AdEntityRepository extends JpaRepository<AdEntity,Long > {
    List<AdEntity> findAll();
    Optional<AdEntity> findById(Integer id);
    List<AdEntity> findByUserEntity_id(Integer id);


}
