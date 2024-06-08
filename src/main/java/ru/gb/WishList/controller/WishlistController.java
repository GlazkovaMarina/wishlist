package ru.gb.WishList.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.service.productService.ProductService;
import ru.gb.WishList.service.giftService.GiftService;
import lombok.extern.java.Log;
import java.util.List;
import ru.gb.WishList.entities.Gift;
import ru.gb.WishList.exception.UserAlreadyExistException;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="WishlistController", description="Контроллер для списка подарков")
@Log
@Controller
@AllArgsConstructor
public class WishlistController {

    private final UserService userService;
    private final ProductService productService;
    private final GiftService giftService;

    @GetMapping("/login")
    public String getLogin() {
        log.severe("Get login");
        return "login.html";
    }
    // @PostMapping("/login") формируется Spring Boot автоматически


    @GetMapping("/registration")
    public String registration (Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", "");
        log.severe("GET registration");
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(User user, Model model) {

        log.severe(user.toString());
        try {
            log.severe("Post registration_DO");
            userService.registrate(user);
            log.severe("Post registration");
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("registration", "user", user);
            model.addAttribute("error", "Аккаунт c таким username/email уже существует. Нажмите _Вход_");
            log.severe("Post registration_catch");
            return mav;
        }
        String returnPage = "redirect:/login";
        return new ModelAndView(returnPage);
    }

    // TODO: добавить из этого примера валидацию
    //@PostMapping("/registration")
    //    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
    //
    //        if (bindingResult.hasErrors()) {
    //            return "registration";
    //        }
    //        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
    //            model.addAttribute("passwordError", "Пароли не совпадают");
    //            return "registration";
    //        }
    //        if (!userService.saveUser(userForm)){
    //            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
    //            return "registration";
    //        }
    //
    //        return "redirect:/";
    //    }

    // TODO: разобраться нужна ли аннотация @PreAuthorize
//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/card_present/{user_id}/{gift_id}")
    public String getCardPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        log.severe("Get card_present");
        return "card_present";
    }


//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit_present/{user_id}/{gift_id}")
    public String getEditPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        model.addAttribute("product_id", gift.getProduct().getId());
        log.severe("Get edit_present");
        return "edit_present";
    }
//    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit_present/{user_id}/{product_id}/{gift_id}")
    public String postEditPresent(@PathVariable("user_id") Long userId, @PathVariable("product_id") Long productId, @PathVariable("gift_id") Long giftId, Gift gift){
        log.severe("Post edit_present");
        gift.setProduct(productService.findProductById(productId));
        giftService.saveGift(gift);
        String returnPage = "redirect:/card_present/" + userId + "/" + giftId;
        return returnPage;
    }

//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/wishlist/{user_id}")
    public String getWishlist(@PathVariable("user_id") Long userId, Model model){
        model.addAttribute("user_id", userId);
        List<Gift> gifts =  giftService.findAllGifts();
        model.addAttribute("gifts", gifts);
        log.severe("Get wishlist");
        return "wishlist";
    }
//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        model.addAttribute("gift", new Gift());
        log.severe("Get new_present");
        return "new_present";
    }
//    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId,  Gift gift){
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(itemId);
        gift.setOwner(user);
        gift.setProduct(product);
        log.severe("Post new_present");
        giftService.saveGift(gift);
        String returnPage = "redirect:/wishlist/" + userId;
        return returnPage;
    }


    //    @PreAuthorize("hasAuthority('USER')")
    // Страница "Редактирование личных данных пользователя"
    @GetMapping("/correct_info/{id}")
    public String getCorrectInfo(@PathVariable("id") Long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("Get correct_info");
        //String returnPage = "/correct_info/" + id; // формируем персональный личный кабинет
        return "correct_info.html";
    }

    //    @PreAuthorize("hasAuthority('USER')")
    // Редактирование личных данных пользователя в БД
    @PostMapping("/correct_info/{id}")
    public String postCorrectInfo(User user, @PathVariable Long id){
        User originUser = userService.findUserById(id);
        log.severe("Post correct_info");
        user.setId(id);
        user.setPassword(originUser.getPassword());
        userService.updateUser(user);
        String returnPage = "redirect:/personal_office/" + id; // формируем персональный личный кабинет
        return returnPage;
    }

    //    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/personal_office/{user_id}")
    public String getPersonalOffice(Model model, @PathVariable("user_id") Long userId){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        log.severe("GET personal_office");
        return "personal_office.html";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/personal_office_admin/{user_id}")
    public String getPersonalOfficeAdmin(Model model, @PathVariable("user_id") Long userId){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        log.severe("GET personal_office");
        return "personal_office_admin.html";
    }

    @GetMapping("/card_item/{user_id}/{item_id}")
    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get card_item");
        return "card_item";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/card_item_admin/{user_id}/{item_id}")
    public String getCardItemAdmin(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get card_item");
        return "card_item_admin";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit_item/{user_id}/{item_id}")
    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get edit_item");
        return "edit_item";
    }

    //        @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit_item/{user_id}/{item_id}")
    public String postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Product product){
        productService.saveProduct(product);
        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
        log.severe("Post edit_item");
        return returnPage;
    }

    //     @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/goods/{user_id}")
    public String getGoods(Model model, @PathVariable("user_id") Long userId){
        model.addAttribute("user_id", userId);
        log.severe("Get goods");
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods";
    }
    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/goods_admin/{user_id}")
    public String getGoodsAdmin(Model model, @PathVariable("user_id") Long userId){
        model.addAttribute("user_id", userId);
        log.severe("Get goods");
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods_admin";
    }
    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new_item/{user_id}")
    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
        log.severe("Get new_item");
        model.addAttribute("user_id", userId);
        return "new_item";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new_item/{user_id}")
    public String postNewItem(@PathVariable("user_id") Long userId, Product product){
        log.severe("Post new_item");
        productService.saveProduct(product);
        String returnPage = "redirect:/goods/" + userId;
        return returnPage;
    }

}


// TODO:  для написания тестов
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