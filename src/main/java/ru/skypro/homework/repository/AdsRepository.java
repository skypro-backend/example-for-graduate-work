package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {


//    List<Ad> findAdsByUser(User user);


}