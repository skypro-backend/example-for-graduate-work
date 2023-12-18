package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.util.Collection;
/**
 * Репозиторий {@code AdRepository} предоставляет методы для взаимодействия
 * с данными рекламных объявлений в базе данных. Расширяет интерфейс
 * {@link org.springframework.data.jpa.repository.JpaRepository}, предоставляя
 * стандартные операции CRUD (Create, Read, Update, Delete) для сущности
 * {@link AdEntity} с использованием типа идентификатора {@code Integer}.
 *
 * <p>Дополнительный метод:</p>
 * <ul>
 *     <li>{@code findAdEntitiesByAuthor} - возвращает коллекцию объявлений,
 *     созданных указанным автором. Реализация данного метода будет автоматически
 *     сгенерирована Spring Data JPA на основе именованного запроса.</li>
 * </ul>
 *
 * <p>Этот репозиторий предоставляет удобный способ доступа к данным
 * рекламных объявлений и интегрируется с механизмом JPA для выполнения
 * стандартных операций над сущностями.</p>
 *
 * @author Michail Z. (GH: HeimTN)
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    Collection<AdEntity> findAdEntitiesByAuthor(UserEntity author);
}
