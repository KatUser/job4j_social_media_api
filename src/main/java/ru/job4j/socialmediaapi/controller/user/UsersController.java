package ru.job4j.socialmediaapi.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.service.user.UserService;

import java.util.List;

@Tag(name = "UsersController", description = "Метод(ы) для взаимодействия с UsersController APIs")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @Operation(
            summary = "Получение всех пользователей",
            description = """
                    Получение всех пользователей в приложении.
                    При успешном выполнении метода возвращается код 200 и список с объектами типа User в теле ответа""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema
                    (implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
