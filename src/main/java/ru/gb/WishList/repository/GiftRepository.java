package ru.gb.WishList.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.gb.WishList.entities.Gift;
import org.springframework.stereotype.Repository;
import  org.springframework.data.jpa.repository.JpaRepository;
@Repository
@Schema(description = "Репозиторий с подарками")
public interface GiftRepository extends JpaRepository<Gift, Long> {
}
