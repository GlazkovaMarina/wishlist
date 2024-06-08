//package ru.gb.WishList.controller;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import ru.gb.WishList.entities.Product;
//import ru.gb.WishList.entities.User;
//import ru.gb.WishList.service.productService.ProductService;
//import java.util.List;
//@Log
//@Controller
//@AllArgsConstructor
//public class ProductController {
//    private final ProductService productService;
//
////    @PreAuthorize("hasAuthority('USER')")
//    @GetMapping("/card_item/{user_id}/{item_id}")
//    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
//        model.addAttribute("user_id", userId);
//        Product product = productService.findProductById(itemId);
//        model.addAttribute("product", product);
//        log.severe("Get card_item");
//        return "card_item";
//    }
//
////    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/card_item_admin/{user_id}/{item_id}")
//    public String getCardItemAdmin(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
//        model.addAttribute("user_id", userId);
//        Product product = productService.findProductById(itemId);
//        model.addAttribute("product", product);
//        log.severe("Get card_item");
//        return "card_item_admin";
//    }
//
////    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/edit_item/{user_id}/{item_id}")
//    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
//        model.addAttribute("user_id", userId);
//        Product product = productService.findProductById(itemId);
//        model.addAttribute("product", product);
//        log.severe("Get edit_item");
//        return "edit_item";
//    }
//
////        @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/edit_item/{user_id}/{item_id}")
//    public String postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Product product){
//        productService.saveProduct(product);
//        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
//        log.severe("Post edit_item");
//        return returnPage;
//    }
//
////     @PreAuthorize("hasAuthority('USER')")
//    @GetMapping("/goods/{user_id}")
//    public String getGoods(Model model, @PathVariable("user_id") Long userId){
//        model.addAttribute("user_id", userId);
//        log.severe("Get goods");
//        List<Product> products =  productService.findAllProducts();
//        model.addAttribute("products", products);
//        return "goods";
//    }
////    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/goods_admin/{user_id}")
//    public String getGoodsAdmin(Model model, @PathVariable("user_id") Long userId){
//        model.addAttribute("user_id", userId);
//        log.severe("Get goods");
//        List<Product> products =  productService.findAllProducts();
//        model.addAttribute("products", products);
//        return "goods_admin";
//    }
////    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/new_item/{user_id}")
//    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
//        log.severe("Get new_item");
//        model.addAttribute("user_id", userId);
//        return "new_item";
//    }
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/new_item/{user_id}")
//    public String postNewItem(@PathVariable("user_id") Long userId, Product product){
//        log.severe("Post new_item");
//        productService.saveProduct(product);
//        String returnPage = "redirect:/goods/" + userId;
//        return returnPage;
//    }
//
//}
