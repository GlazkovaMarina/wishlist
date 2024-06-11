package ru.gb.WishList.service.productService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.gb.WishList.entities.Product;

@Schema(description = "Сервис товара")
public interface ProductService {

    @Operation(summary = "Найти все товары",
            description = "Вывод всех товаров маркетплейса")
    public List<Product> findAllProducts();

    @Operation (summary = "Найти подарок по id",
            description = "Поиск подарка по идентификатору")
    public Product findProductById(Long id);

    @Operation (summary = "Сохранить подарок в БД",
            description = "Добавить подарок в базу данных")
    public Product saveProduct(Product product);
}
