package ru.gb.WishList.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.Product;
import org.springframework.stereotype.Repository;
import  org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Tag(name="ProductRepository", description="Репозиторий с товарами")
public interface ProductRepository extends JpaRepository<Product, Long> {
}