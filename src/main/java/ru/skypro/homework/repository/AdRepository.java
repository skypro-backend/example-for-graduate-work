package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ad;

import java.util.List;


public interface AdRepository extends JpaRepository<Ad, Integer> {
    Ad findByPk(Integer pk);
    List<Ad> findAdsByUser_UsernameContains(String userName);
}
