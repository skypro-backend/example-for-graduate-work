package ru.skypro.sitesforresaleofthings.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.sitesforresaleofthings.entity.Ad;

/**
 * Создаем репозиторий для сущности "Объявление"
 */
public interface AdRepository extends JpaRepository<Ad, Integer> {
}