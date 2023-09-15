package ru.skypro.homework.repository.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.roles.Role;
import ru.skypro.homework.entity.roles.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles findByRole(Role role);
}
