package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Collection<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        if (repository.duplicateEmail(user.getEmail()) == 1) {
            throw new ConflictException("Данный Email уже занят");
        }
        return repository.save(user);
    }

    @Override
    public User update(UpdateUserRequest request, final Long userId) {

        User user = getUserById(userId);

        if (request.hasEmail()
                && !request.getEmail().equals(user.getEmail())
                && repository.duplicateEmail(request.getEmail()) > 0) {
            throw new ConflictException("Данный Email уже занят");
        }

        UserMapper.updateUserFields(user, request);
        return repository.update(user);
    }

    @Override
    public User getUserById(final Long id) {
        return repository.getUserById(id).orElseThrow(() ->
                new NotFoundException("Пользователя с id = " + id + " не существует"));
    }

    @Override
    public void delete(final Long id) {
        getUserById(id);
        repository.delete(id);
    }
}