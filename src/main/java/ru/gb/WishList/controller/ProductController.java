package ru.gb.WishList.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.gb.WishList.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.service.productService.ProductService;
import lombok.extern.java.Log;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name="ProductController", description="Контроллер товаров")
@Log
@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/card_item/{user_id}/{item_id}")
    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        return "card_item";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit_item/{user_id}/{item_id}")
    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        return "edit_item";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit_item/{user_id}/{item_id}")
    public String postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Product product){
        productService.saveProduct(product);
        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
        return returnPage;
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/goods/{user_id}")
    public String getGoods(Model model, @PathVariable("user_id") Long userId){
        model.addAttribute("user_id", userId);
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new_item/{user_id}")
    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
        model.addAttribute("user_id", userId);
        return "new_item";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new_item/{user_id}")
    public String postNewItem(@PathVariable("user_id") Long userId, Product product){
        productService.saveProduct(product);
        String returnPage = "redirect:/goods/" + userId;
        return returnPage;
    }

}
