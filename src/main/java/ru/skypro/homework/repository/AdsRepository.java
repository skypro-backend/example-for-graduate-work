package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdsModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<AdsModel, Integer> {
    List<AdsModel> findAllByUser_Pk( Integer id );

    Optional<AdsModel> findAdsModelByTitleIsLike( String title );
}
