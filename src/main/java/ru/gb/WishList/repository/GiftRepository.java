package ru.gb.WishList.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.Gift;
import org.springframework.stereotype.Repository;
import  org.springframework.data.jpa.repository.JpaRepository;
@Repository
@Tag(name="GiftRepository", description="Репозиторий с подарками")
public interface GiftRepository extends JpaRepository<Gift, Long> {
}
