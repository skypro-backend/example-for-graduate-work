package ru.skypro.kakavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.model.Ad;

import java.util.List;

@Repository
public interface AdRepo extends JpaRepository <Ad, Integer> {

    List<Ad> findAllByUserId(Long id);
//    Ad findByPk(int pk);
}
