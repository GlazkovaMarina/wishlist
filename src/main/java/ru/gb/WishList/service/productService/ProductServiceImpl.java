package ru.gb.WishList.service.productService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.WishList.entities.Image;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.exception.ProductScoreIsNotCorrect;
import ru.gb.WishList.exception.ProductWithSuchIdNotFoundException;
import ru.gb.WishList.repository.ImageRepository;
import ru.gb.WishList.repository.ProductRepository;

import java.io.IOException;
import java.util.Optional;

import java.util.List;
@Service
@AllArgsConstructor
@Log
@Tag(name="ProductServiceImpl", description="Сервис товара")
public class ProductServiceImpl implements ProductService{

    @Schema(description = "Репозиторий товара")
    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

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
    @Override
    public Product saveProduct(Product product, MultipartFile file) throws IOException{
        if (product.getScore() < 0 || product.getScore() > 5){
            throw new ProductScoreIsNotCorrect("Рейтинг должен быть значением от 0 до 5 включительно");
        }
        Image image;
        log.info("saveProduct");
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            log.info("do image.toString");
            //product.addImageToProduct(image);
            log.info("after addImageToProduct");
            image = imageRepository.save(image);
            log.info(image.getId().toString());
            product.setImage(image);
        }
        return productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

}
