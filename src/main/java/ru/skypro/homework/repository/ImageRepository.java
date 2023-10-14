package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.ImageEntity;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    Optional<ImageEntity> findByUserEntityId(Integer userId);

    /**
     * Метод для поиска пути файла по userId
     * @param userId id пользователя
     * @return путь файла пользователя
     */
    @Query(value = "SELECT file_path FROM image_entity i WHERE i.user_entity_id = ?1", nativeQuery = true)
    String findFilePathByUserEntityId(Integer userId);

}
