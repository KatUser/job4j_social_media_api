package ru.job4j.socialmediaapi.repository.picture;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Picture;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
}
