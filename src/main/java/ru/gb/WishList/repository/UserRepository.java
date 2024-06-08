package ru.gb.WishList.repository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import  org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);
}