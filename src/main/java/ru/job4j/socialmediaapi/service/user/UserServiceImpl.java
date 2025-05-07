package ru.job4j.socialmediaapi.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.user.UserRepository;
import ru.job4j.socialmediaapi.security.dtos.request.SignupRequestDTO;
import ru.job4j.socialmediaapi.security.dtos.response.RegisterDTO;
import ru.job4j.socialmediaapi.model.ERole;
import ru.job4j.socialmediaapi.model.Role;
import ru.job4j.socialmediaapi.repository.role.RoleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public RegisterDTO signUp(SignupRequestDTO signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByName(signUpRequest.getName()))
                || Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new RegisterDTO(HttpStatus.BAD_REQUEST, "Error: Username or Email is already taken!");
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Supplier<RuntimeException> supplier = () -> new RuntimeException("Error: Role is not found.");

        if (strRoles == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(supplier));
                    case "mod" -> roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(supplier));
                    default -> roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
                }
            });
        }

        var user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setSubscriber(new ArrayList<>());
        user.setRole(roles);
        userRepository.save(user);
        return new RegisterDTO(HttpStatus.OK, "Person registered successfully!");
    }
}
