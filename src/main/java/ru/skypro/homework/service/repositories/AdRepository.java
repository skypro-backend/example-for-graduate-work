package ru.skypro.homework.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.CommentEntity;

import java.util.List;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
        @Query("SELECT ad FROM AdEntity ad WHERE ad.user.email = :email")
        List<AdEntity> findByEmail(@Param("email") String email);
}
