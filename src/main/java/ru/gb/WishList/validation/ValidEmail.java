// todo: при успешной безопасности, вернуть валидацию

//package ru.gb.WishList.validation;
//import jakarta.validation.Payload;
//import java.lang.annotation.Documented;
//import jakarta.validation.Constraint;
//import java.lang.annotation.Target;
//import java.lang.annotation.Retention;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({TYPE, FIELD, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = EmailValidator.class)
//@Documented
//public @interface ValidEmail {
//    String message() default "Invalid email";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
