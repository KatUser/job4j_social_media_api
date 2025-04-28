package ru.job4j.socialmediaapi.controller.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.post.PostService;
import ru.job4j.socialmediaapi.service.user.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/postsbyusers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getPostsByUsers(@RequestBody List<Long> userIdList) {
        return userService.getUsersDtos(userIdList);
    }

}
