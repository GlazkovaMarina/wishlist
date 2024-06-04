package ru.gb.WishList.controller;

import lombok.AllArgsConstructor;
import ru.gb.WishList.domain.Product;
import ru.gb.WishList.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.service.productService.ProductService;
import ru.gb.WishList.service.giftService.GiftService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.java.Log;
import java.util.List;
import ru.gb.WishList.domain.Gift;
import ru.gb.WishList.domain.Status;
import ru.gb.WishList.domain.Priority;




@Log
@Controller
@AllArgsConstructor
public class WishListController {

    private final UserService userService;
    private final ProductService productService;
    private final GiftService giftService;

    @GetMapping("/index")
    public String getStartPage(){
        return "index";
    }


    // Страница "Редактирование личных данных пользователя"
    @GetMapping("/correct_info/{id}")
    public String getCorrectInfo(@PathVariable("id") Long id, Model model){
        // TODO: конкретного пользователя подставлять!
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("Get correct_info");
        //String returnPage = "/correct_info/" + id; // формируем персональный личный кабинет
        return "correct_info.html";
    }

    // Редактирование личных данных пользователя в БД
    @PostMapping("/correct_info/{id}")
    public String postCorrectInfo(User user, @PathVariable Long id){
        log.severe("Post correct_info");
        userService.saveUser(user);
        String returnPage = "redirect:/personal_office/" + id; // формируем персональный личный кабинет
        return returnPage;
    }


    @GetMapping("/personal_office/{id}")
    public String getPersonalOffice(Model model, @PathVariable Long id){
        // TODO: убрать готового юзера, считать из бд
//        User user = new User();
//        user.setLastName("Ivanov");
//        user.setFirstName("Ivan");
//        user.setSurname("Ivanovich");
//        user.setNumber(79009090909L);
//        user.setEmail("q@q.ru");
//        String date = "2016-08-16";
//        LocalDate localDate = LocalDate.parse(date);
//        user.setBirthday(localDate);
//        userService.save(user);
        // TODO: конкретного пользователя подставлять!
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("GET personal_office");
        return "personal_office.html";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        log.severe("GET registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user){
        userService.saveUser(user);
        String returnPage = "redirect:/personal_office/" + user.getId(); // формируем персональный личный кабинет
        return returnPage;
    }
    @GetMapping("/entry")
    public String getEntry(){
        log.severe("Get entry");
        return "entry";
    }

    @GetMapping("/card_item/{user_id}/{item_id}")
    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get card_item");
        return "card_item";
    }

    @GetMapping("/card_present/{user_id}/{gift_id}")
    public String getCardPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        log.severe("Get card_present");
        return "card_present";
    }
    @GetMapping("/edit_item/{user_id}/{item_id}")
    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get edit_item");
        return "edit_item";
    }

    @PostMapping("/edit_item/{user_id}/{item_id}")
    public String postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Product product){
        productService.saveProduct(product);
        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
        log.severe("Post edit_item");
        return returnPage;
    }

    @GetMapping("/edit_present")
    public String getEditPresent(){
        log.severe("Get edit_present");
        return "edit_present";
    }
    @GetMapping("/goods/{id}")
    public String getGoods(Model model, @PathVariable Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("Get goods");
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods";
    }

    @GetMapping("/new_item/{user_id}")
    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
        log.severe("Get new_item");
        model.addAttribute("user_id", userId);
        return "new_item";
    }

    @PostMapping("/new_item/{user_id}")
    public String postNewItem(@PathVariable("user_id") Long userId, Product product){
        log.severe("Post new_item");
        productService.saveProduct(product);
        String returnPage = "redirect:/goods/" + userId;
        return returnPage;
    }

    @GetMapping("/wishlist/{user_id}")
    public String getWishlist(@PathVariable("user_id") Long userId, Model model){
        model.addAttribute("user_id", userId);
        List<Gift> gifts =  giftService.findAllGifts();
        model.addAttribute("gifts", gifts);
        log.severe("Get wishlist");
        return "wishlist";
    }

    @GetMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        //User user = userService.findUserById(userId);
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        model.addAttribute("gift", new Gift());
        log.severe("Get new_present");
        return "new_present";
    }

    @PostMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId,  Gift gift){
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(itemId);
//        Gift newGift = new Gift();
//        newGift.setComment(gift.getComment());
//        newGift.setStatus(gift.getStatus());
//        newGift.setPriority(gift.getPriority());
//        newGift.setOwner(user);
//        newGift.setProduct(product);
        gift.setOwner(user);
        gift.setProduct(product);
        log.severe("Post new_present");
        giftService.saveGift(gift);
        String returnPage = "redirect:/wishlist/" + userId;
        return returnPage;
    }


}