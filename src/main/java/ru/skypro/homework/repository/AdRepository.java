package ru.skypro.homework.repository;

import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {

}
