package ru.job4j.socialmediaapi.repository.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.socialmediaapi.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
}
