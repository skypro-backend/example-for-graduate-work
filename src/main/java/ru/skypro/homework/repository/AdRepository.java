package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    void removeAdById(Integer id);

    Ad getAdById(Integer id);

    List<Ad> getAdsByUserId(Integer id);


}
