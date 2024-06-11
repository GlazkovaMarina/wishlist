package ru.gb.WishList.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import  org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Schema(description = "Репозиторий с пользователями")
public interface UserRepository extends JpaRepository<User, Long> {

    @Operation(
            summary = "Поиск пользователя по электронной почте email",
            description = "Позволяет найти пользователя в базе данных через его электронную почту email. Метод возвращает Optional<User>"
    )
    Optional<User> findByUsername(@Parameter(description = "Электронная почта пользователя")String email);
}