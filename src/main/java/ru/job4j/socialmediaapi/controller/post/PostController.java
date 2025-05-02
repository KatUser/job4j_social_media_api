package ru.job4j.socialmediaapi.controller.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "PostController", description = "Метод(ы) для взаимодействия с PostController API")
@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    private final PictureService pictureService;

    @Operation(
            summary = "Получение поста по его идентификатору.",
            description = """
                    Получение поста по его идентификатору.
                    При успешном выполнении метода возвращается код 200 и объект типа Post в теле ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Post.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId")
                                        @NotNull
                                        @Min(value = 1, message = "Номер ресурса должен быть 1 и более.")
                                        Long postId) {
        return postService.getPostById(postId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Создание поста.",
            description = """
                    Создание объекта поста.
                    При успешном выполнении метода возвращается код 201 и объект типа Post в теле ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Post.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
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

    @Operation(
            summary = "Изменение поста по его идентификатору.",
            description = """
                    Изменение поста по его идентификатору.
                    При успешном выполнении метода возвращается код 200 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @PutMapping
    public ResponseEntity<Void> updatePost(@Valid @RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Изменение поста по его идентификатору",
            description = """
                    Изменение поста по его идентификатору.
                    При успешном выполнении метода возвращается код 200 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @PatchMapping
    public ResponseEntity<Void> patchPost(@Valid @RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Удаление поста по его идентификатору.",
            description = """
                    Удаление поста по его идентификатору.
                    При успешном выполнении метода возвращается код 204 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable("postId")
                                               @NotNull
                                               @Min(value = 1,
                                                       message = "Номер ресурса должен быть 1 и более.")
                                               Long postId) {
        if (postService.deletePostById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
