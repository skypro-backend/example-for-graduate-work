package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Long, Advertisement> {
    void save(Advertisement advertisement);

    void delete(Advertisement advertisement);
}
