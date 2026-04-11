package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс для работы с объявлениями в базе данных.
 */
@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Integer> {
    /**
     * Возвращает объявления, созданные конкретным пользователем.
     *
     * @param author автор объявлений
     * @return список объявлений пользователя
     */
    List<AdvertisementEntity> findAllByAuthor(UserEntity author);

    /**
     * Возвращает email автора объявления по идентификатору объявления.
     *
     * @param adId идентификатор объявления
     * @return email автора, если объявление найдено
     */
    Optional<Object> findAuthorEmailById(Integer adId);
}
