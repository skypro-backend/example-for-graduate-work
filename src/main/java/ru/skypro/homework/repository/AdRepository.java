package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

/**
 * Репозиторий объявлений.
 */
public interface AdRepository
        extends JpaRepository<AdEntity, Long> {

    /**
     * Возвращает объявления указанного пользователя.
     *
     * @param author автор объявлений
     * @return объявления пользователя
     */
    List<AdEntity> findAllByAuthorOrderByIdDesc(
            UserEntity author
    );

    /**
     * Возвращает все объявления от новых к старым.
     *
     * @return список объявлений
     */
    List<AdEntity> findAllByOrderByIdDesc();
}