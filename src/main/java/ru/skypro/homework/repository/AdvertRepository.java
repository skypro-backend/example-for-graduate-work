package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Advert;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {
    List<Advert> findByAuthorId(int userId);
    Optional<Advert> findByTitle(String title);
    void deleteByTitle(String title);
}
