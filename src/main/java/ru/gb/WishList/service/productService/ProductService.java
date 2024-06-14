package ru.gb.WishList.service.productService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.Product;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Tag(name="ProductService", description="Сервис товара")
public interface ProductService {

    @Operation(summary = "Найти все товары",
            description = "Вывод всех товаров маркетплейса")
    public List<Product> findAllProducts();

    @Operation (summary = "Найти товар по id",
            description = "Поиск товара по идентификатору")
    public Product findProductById(Long id);

    @Operation (summary = "Сохранить товар в БД",
            description = "Добавить товар в базу данных")
    public Product saveProduct(Product product, MultipartFile file) throws IOException;

    @Operation (summary = "Удалить товар",
            description = "Удалить товар из базы данных")
    public void deleteProduct(Long id);
}
