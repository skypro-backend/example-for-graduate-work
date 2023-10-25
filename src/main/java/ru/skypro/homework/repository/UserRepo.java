package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.ExtendedAd;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

/*    //    @Query(value = "SELECT * FROM users u WHERE u.email = :email)",
//            nativeQuery = true)
    @Query(value = "SELECT user_id AS userId, role AS userRole, email, password FROM users WHERE email = :email",
            nativeQuery = true)
    Optional<UserModel> findUserByEmail(@Param("email") String email);*/

    Optional<UserModel> findByUserName(String userName);


//    @Query(value = "SELECT ad.ad_id AS pk, users.first_name AS authorFirstName, " +
//            "users.last_name AS authorLastName, ad.description, users.email, users.image, " +
//            "users.phone, ad.price, ad.title " +
//            "FROM users JOIN ad on users.user_id = :id",
//            nativeQuery = true)


    // что-то не работает(
    @Query(value = """
            SELECT ad.ad_id AS pk, users.first_name AS authorFirstName, users.last_name AS authorLastName,
            ad.description, users.user_name AS email, ad.ad_image,
            users.phone, ad.price, ad.title
            FROM users
            JOIN ad ON ad.ad_id = :id""",
            nativeQuery = true)

    Optional<ExtendedAd> getExtendedAd(@Param("id") int id);
}
