package ru.gb.WishList.service.productService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.repository.ProductRepository;
import java.util.Optional;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    public Product findProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return product;
        } else {
            // TODO: переделать возврат, чтоб не было ошибок. Добавить исключение
            return null;
        }
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

}
