package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getAllUsers(@Positive @PathVariable final Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User saveNewUser(@Valid @RequestBody final UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PatchMapping("/{userId}")
    public User update(@Valid @RequestBody UpdateUserRequest request,
                       @Positive @PathVariable final Long userId) {
        return userService.update(request, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Positive @PathVariable final Long id) {
        userService.delete(id);
    }

}
