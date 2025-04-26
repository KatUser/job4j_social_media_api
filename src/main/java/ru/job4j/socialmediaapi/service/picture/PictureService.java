package ru.job4j.socialmediaapi.service.picture;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;

import java.util.List;

public interface PictureService {

    @Transactional
    void savePicture(Picture picture);

    List<Picture> getAllPictures();
}
