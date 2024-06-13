package ru.gb.WishList.service.productService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.exception.ProductScoreIsNotCorrect;
import ru.gb.WishList.exception.ProductWithSuchIdNotFoundException;
import ru.gb.WishList.repository.ProductRepository;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import java.util.List;
@Service
@AllArgsConstructor
@Log
@Tag(name="ProductServiceImpl", description="Сервис товара")
public class ProductServiceImpl implements ProductService{

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img"; // рабочий каталог пользователя

    @Schema(description = "Репозиторий товара")
    private final ProductRepository productRepository;

    @Operation(summary = "Найти все товары",
            description = "Вывод всех товаров маркетплейса")
    public List<Product> findAllProducts(){
        log.info("findAllProducts()");
        return productRepository.findAll();
    }

    @Operation (summary = "Найти подарок по id",
            description = "Поиск подарка по идентификатору")
    public Product findProductById(Long productId){
        log.info("findProductById()");
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new ProductWithSuchIdNotFoundException("Не найден подарок с таким идентификатором " + productId);
        }
        return productOptional.get();
    }

    @Operation (summary = "Сохранить подарок в БД",
            description = "Добавить подарок в базу данных")
    @Override
    public Product saveProduct(Product product, MultipartFile file) throws IOException{
        log.info("saveProduct()");
        if (product.getScore() < 0 || product.getScore() > 5){
            throw new ProductScoreIsNotCorrect("Рейтинг должен быть значением от 0 до 5 включительно");
        }
        if (!file.isEmpty())
            product.setImagePathFS(uploadFile(file));
        return productRepository.save(product);
    }
    public String uploadFile(MultipartFile file) throws IOException{
        log.info("uploadFile()");
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        return fileNames.toString();
    }

}
