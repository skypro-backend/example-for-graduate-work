package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;

import java.util.List;
@Repository
public interface AdsRepository extends JpaRepository<Ads,Integer> {
    /**
     * Метод написан используя параметр @Query сортированный по id
     * @return сортированный лист объявлений
     */
    @Query(value = "select * from ads order by id", nativeQuery = true)
    List<Ads> findAllAds();

    /**
     *  Метод написан с использованием ключевых слов Containing и IgnoreCase
     * @param title строка названия объявления
     * @return лист объявлений с таким названием
     */
    List<Ads> findByTitleContainingIgnoreCase(String title);

    List<Ads> findAllByUser (User user);
}
