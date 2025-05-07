package ru.job4j.socialmediaapi.controller.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.post.PostService;
import ru.job4j.socialmediaapi.service.user.UserService;

import java.util.List;

@Tag(name = "PostsController", description = "Метод(ы) для взаимодействия с PostsController API")
@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostService postService;

    private final UserService userService;

    @Operation(
            summary = "Получение всех постов в приложении",
            description = """
                    Получение всех постов в приложении.
                    При успешном выполнении метода возвращается код 200
                    и список с объектами типа Post в теле ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema
                    (implementation = Post.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @Operation(
            summary = "Получение всех пользователей и их постов",
            description = """
                    Получение всех пользователей и их постов в приложении.
                    В теле запросов передаётся массив с индентификатором(ами) пользователя(ей)
                    При успешном выполнении метода возвращается код 200 и список с объектами типа UserDTO
                    с вложенным списком с объектами типа Post.
                    """)
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema
                    (implementation = UserDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @RequestMapping(value = "/postsbyusers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getPostsByUsers(@RequestBody List<Long> userIdList) {
        return userService.getUsersDtos(userIdList);
    }

}
