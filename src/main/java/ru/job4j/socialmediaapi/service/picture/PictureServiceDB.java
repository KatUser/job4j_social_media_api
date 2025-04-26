package ru.job4j.socialmediaapi.service.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.repository.picture.PictureRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PictureServiceDB implements PictureService {

    private final PictureRepository pictureRepository;

    @Override
    @Transactional
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }
}
