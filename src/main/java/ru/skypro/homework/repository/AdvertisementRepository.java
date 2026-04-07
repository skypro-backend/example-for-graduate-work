package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Long> {
List<AdvertisementEntity> findAllByAuthor(UserEntity author);
}
