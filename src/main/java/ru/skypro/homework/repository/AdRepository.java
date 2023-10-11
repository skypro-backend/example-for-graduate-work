package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.pojo.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT ad FROM Ad ad WHERE ad.user.userID = :userId")
    List<Ad> findAdsByUserId(@Param("userId") Long userId);

}
