// todo: при успешной безопасности, вернуть валидацию

//package ru.gb.WishList.validation;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import ru.gb.WishList.entities.User;
//
//public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
//
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//    @Override
//    public boolean isValid(Object obj, ConstraintValidatorContext context){
//        User user = (User) obj;
//        return user.getPassword().equals(user.getMatchingPassword());
//    }
//}