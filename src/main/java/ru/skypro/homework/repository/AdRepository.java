package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad,Integer> {
    List<Ad> findAllByAuthor(User author);

    @Override
    Optional<Ad> findById(Integer id);

    void deleteById(Integer id);
}
