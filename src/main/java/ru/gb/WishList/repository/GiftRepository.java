package ru.gb.WishList.repository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.WishList.domain.Gift;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import  org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.WishList.domain.Product;
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
}
