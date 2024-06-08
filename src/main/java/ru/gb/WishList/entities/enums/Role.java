package ru.gb.WishList.entities.enums;

import org.springframework.security.core.GrantedAuthority;



public enum Role implements GrantedAuthority
{
    USER;

    @Override
    public String getAuthority()
    {
        return name();
    }
}
 // TODO: убрать лишние вариации role
//
//import org.springframework.security.core.GrantedAuthority;
////public enum Role {
////    USER("USER"),
////    ADMIN("ADMIN");
////
////    private final String label;
////
////    Role(String label) {
////        this.label = label;
////    }
////}
//
//
//public enum Role
//{
//    USER("Пользователь"),
//    ADMIN("Администратор");
//    public String getRole()
//    {
//        return name();
//    }
//    public final String label;
//    private Role(String label) {
//        this.label = label;
//    }
//}

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.gb.WishList.entities.User;
//
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import jakarta.persistence.Entity;
//import java.util.Set;

//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "roles")
//public class Role implements GrantedAuthority {
//    @Id
//    private Long id;
//    private String name;
//
////    @ManyToMany(mappedBy = "roles")
////    private Set<User> users;
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//}