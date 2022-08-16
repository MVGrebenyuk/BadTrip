package ru.alexsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexsolution.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
