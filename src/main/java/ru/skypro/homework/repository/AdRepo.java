package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.projections.ExtendedAd;

import java.util.Optional;

@Repository
public interface AdRepo extends JpaRepository<AdModel, Integer> {

    // Потом наверное придется исправить
    @Query(value = "SELECT ad_id AS pk, first_name AS authorFirstName, last_name AS authorLastName," +
            " description, email, image, phone, price, title " +
            "FROM ad JOIN users WHERE ad_id = user_id",
            nativeQuery = true)
    Optional<ExtendedAd> getExtendedAd(@Param("id") int id);


//    @Query(value = "SELECT * FROM ad WHERE user_id = :id;",
//            nativeQuery = true)
//    List<AdModel> findAdByUserId(@Param("id") int id);
}
