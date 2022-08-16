package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsolution.entity.Role;

import java.util.Collection;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Collection<Role> findAllByName(String roleName);
}
