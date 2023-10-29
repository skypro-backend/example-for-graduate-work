package ru.skypro.homework.repository;

import ru.skypro.homework.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Long, Advertisement> {
    void save(Advertisement advertisement);
    void delete(Advertisement advertisement);

}
