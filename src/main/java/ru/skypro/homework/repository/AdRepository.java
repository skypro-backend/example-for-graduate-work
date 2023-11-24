package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findAdByUser(User user);

    Ad findByPk(int pk);

    List<Ad> findById(int userIdAd);



//    void deleteById(int pk);

//    Ad findAdByUser_Id(int pk);
//
//    AdDto deleteById(int pk);

}


