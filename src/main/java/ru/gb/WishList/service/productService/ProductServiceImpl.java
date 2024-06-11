package ru.gb.WishList.service.productService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.exception.ProductScoreIsNotCorrect;
import ru.gb.WishList.exception.ProductWithSuchIdNotFoundException;
import ru.gb.WishList.repository.ProductRepository;
import java.util.Optional;

import java.util.List;
@Service
@AllArgsConstructor
@Tag(name="ProductServiceImpl", description="Сервис товара")
public class ProductServiceImpl implements ProductService{

    @Schema(description = "Репозиторий товара")
    private final ProductRepository productRepository;

    @Operation(summary = "Найти все товары",
            description = "Вывод всех товаров маркетплейса")
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Operation (summary = "Найти подарок по id",
            description = "Поиск подарка по идентификатору")
    public Product findProductById(Long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new ProductWithSuchIdNotFoundException("Не найден подарок с таким идентификатором " + productId);
        }
        return productOptional.get();
    }

    @Operation (summary = "Сохранить подарок в БД",
            description = "Добавить подарок в базу данных")
    public Product saveProduct(Product product){
        if (product.getScore() < 0 || product.getScore() > 5){
            throw new ProductScoreIsNotCorrect("Рейтинг должен быть значением от 0 до 5 включительно");
        }
        return productRepository.save(product);
    }

}
