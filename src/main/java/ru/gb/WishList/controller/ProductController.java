package ru.gb.WishList.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.gb.WishList.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.exception.ProductScoreIsNotCorrect;
import ru.gb.WishList.service.productService.ProductService;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name="ProductController", description="Контроллер товаров")
@Log
@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/card_item/{user_id}/{item_id}")
    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        log.info("getCardItem()");
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        return "card_item";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit_item/{user_id}/{item_id}")
    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        log.info("getEditItem()");
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("error", "");
        model.addAttribute("product", product);
        return "edit_item";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit_item/{user_id}/{item_id}")
    public ModelAndView postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, @RequestParam("file") MultipartFile file, Product product, Model model) throws IOException{
        log.info("postEditItem()");
        try{
            productService.saveProduct(product, file);
        } catch (ProductScoreIsNotCorrect exc){
            String errorPage = "/edit_item";
            ModelAndView mav = new ModelAndView(errorPage, "product", product);
            model.addAttribute("error", exc.getMessage());
            return mav;
        }
        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
        return new ModelAndView(returnPage);
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/goods/{user_id}")
    public String getGoods(Model model, @PathVariable("user_id") Long userId){
        log.info("getGoods()");
        model.addAttribute("user_id", userId);
        List<Product> products =  productService.findAllProducts();
        if (products.isEmpty()){
            model.addAttribute("response", "NoData");
        }
        else{
            model.addAttribute("response", "Data");
        }
        model.addAttribute("products", products);
        return "goods";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new_item/{user_id}")
    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
        log.info("getNewItem()");
        model.addAttribute("user_id", userId);
        return "new_item";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/new_item/{user_id}")
    public String postNewItem(@PathVariable("user_id") Long userId, @RequestParam("file") MultipartFile file, Product product) throws IOException{
        log.info("postNewItem()");
        productService.saveProduct(product, file);
        String returnPage = "redirect:/goods/" + userId;
        return returnPage;
    }

}
