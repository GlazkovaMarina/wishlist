package ru.gb.WishList.service.productService;
import java.util.List;
import ru.gb.WishList.entities.Product;

public interface ProductService {
    public List<Product> findAllProducts();
    public Product findProductById(Long id);
    public Product saveProduct(Product product);
}
