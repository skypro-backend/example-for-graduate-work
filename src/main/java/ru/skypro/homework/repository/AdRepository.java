package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ad;

public interface AdRepository extends CrudRepository<Ad, Integer> {

}
