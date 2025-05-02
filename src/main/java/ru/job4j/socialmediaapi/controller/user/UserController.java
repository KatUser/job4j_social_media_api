package ru.job4j.socialmediaapi.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.service.user.UserService;

@Tag(name = "UserController", description = "Метод(ы) для взаимодействия с UserController API")
@Validated
@Slf4j
@AllArgsConstructor
@RestController
@ControllerAdvice
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Получение пользователя по его идентификатору.",
            description = """
                    Получение пользователя по его идентификатору.
                    При успешном выполнении метода в теле ответа возвращается код 200 и объект типа User.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId")
                                        @NotNull
                                        @Min(value = 1, message = "Номер ресурса должен быть 1 и более.")
                                        Long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Создание пользователя.",
            description = """
                    Создание объекта пользователя
                    При успешном выполнении метода в теле ответа возвращается код 201 и объект типа User.""")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = User.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @Operation(
            summary = "Изменение пользователя по его идентификатору.",
            description = """
                    Изменение пользователя по его идентификатору.
                    При успешном выполнении метода возвращается код 200 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (userService.update(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Изменение пользователя по его идентификатору.",
            description = """
                    Изменение пользователя по его идентификатору.
                    При успешном выполнении метода возвращается код 200 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PatchMapping
    public ResponseEntity<User> patchUser(@RequestBody User user) {
        if (userService.update(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Удаление пользователя по его идентификатору.",
            description = """
                    Удаление пользователя по его идентификатору.
                    При успешном выполнении метода возвращается код 204 с пустым телом ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId")
                                               @NotNull
                                               @Min(value = 1, message = "Номер ресурса должен быть 1 и более.")
                                               Long userId) {
        if (userService.deleteById(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
