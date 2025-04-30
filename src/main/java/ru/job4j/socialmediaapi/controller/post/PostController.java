package ru.job4j.socialmediaapi.controller.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.picture.PictureService;
import ru.job4j.socialmediaapi.service.post.PostService;

@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    private final PictureService pictureService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId")
                                        @NotNull
                                        @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                        Long postId) {
        return postService.getPostById(postId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        post.getPicture().forEach(pictureService::savePicture);
        post.getPicture().forEach(p -> p.setPost(post));
        postService.savePost(post);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).body(post);
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<Void> patchPost(@RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        if (postService.deletePostById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
