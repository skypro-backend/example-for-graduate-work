package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findByUserEmail(String email);

    boolean existsByIdAndUserEmail(Integer id, String email);

}
