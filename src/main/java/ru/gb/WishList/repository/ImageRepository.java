package ru.gb.WishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.WishList.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}