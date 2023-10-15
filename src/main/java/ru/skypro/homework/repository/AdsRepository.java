package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
    /**
     * поиск объявления по названию
     */

    List<Ad> findByDescriptionContainingIgnoreCase(String description);

    /**
     * поиск объвления по автору
     */

    List<Ad> findByAuthor(User author);


}
