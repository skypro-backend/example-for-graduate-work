package ru.skypro.homework.service.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.service.entities.AdsEntity;

public interface AdsRepository extends JpaRepository<AdsEntity, Integer> {

}
