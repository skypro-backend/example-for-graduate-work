package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface AdEntityRepository extends JpaRepository<AdEntity,Long > {
    void deleteById(Integer id);
    List<AdEntity> findAll();
    Optional<AdEntity> findById(Integer id);
//    Optional<AdEntity> findByImageEntity_filePath(String filePath);

    List<AdEntity> findByUserEntity_id(Integer id);


}
