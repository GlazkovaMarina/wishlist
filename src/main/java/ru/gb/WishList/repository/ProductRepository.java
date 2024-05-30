package ru.gb.WishList.repository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.WishList.domain.Product;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import  org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}